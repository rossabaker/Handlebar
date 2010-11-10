package com.recursivity.bowler

import collection.mutable.{MutableList, HashMap}
import reflect.BeanProperty

/**
 * An attachable that may be the parent of other Attachables, for instance a parent node in a hierarchical tree of template id's, or for a listable.
 */

class Container(componentId: Option[String]) extends Attachable {
  if (this.isInstanceOf[Listable])
    throw new IllegalStateException(getClass.getName + "is both a Container and Listable. A Container cannot be a Listable!")

  @BeanProperty
  var locale: String = null

  val children = new MutableList[Attachable]
  val childIndex = new HashMap[String, Int]

  def getChildren = children.toList

  def get(id: String): Option[Attachable] = {
    try{
      val index = childIndex(id)
      return children.get(index)
    }catch{
      case e: NoSuchElementException => {return None}
    }
  } 
  
  /**
   * adds a child Attachable to this Container
   */
  def add(child: Attachable) {
    var index = -1
    try {
      index = childIndex(child.id.get)
    } catch {
      case e: NoSuchElementException => {index = -1}
    }
    if (index == -1) {
      childIndex += child.id.get -> children.length
      children += child
    } else
      children.update(index, child)
  }

  def id = componentId


  /**
   * gets the Model for the template of this Container.
   */
  def getModel: Map[String, Any] = {
    return getModel(children)

  }

  private def getModel(tree: MutableList[Attachable]): Map[String, Any] = {
    val map = new HashMap[String, Any]

    children.foreach(child => {
      visitLocale(child)

      if (child.isInstanceOf[Cacheable]){
        map += child.id.get -> child.asInstanceOf[Cacheable].renderCached
      }else if (child.isInstanceOf[Renderable])
        map += child.id.get -> child.asInstanceOf[Renderable].render
      else if (child.isInstanceOf[Listable]) {
        traverseListable(child.asInstanceOf[Listable], map)
      } else if (child.isInstanceOf[Container]) {
        map += child.id.get -> child.asInstanceOf[Container].getModel
      }

    })
    return map.toMap
  }


  private def traverseListable(listable: Listable, map: HashMap[String, Any]) {
    val listableChildren = new MutableList[Any]

    listable.getList.foreach(listChild => {
      visitLocale(listChild)
      if (listChild.isInstanceOf[Renderable]) {
        if (listChild.id == None)
          listableChildren += listChild.asInstanceOf[Renderable].render
        else
          listableChildren += Map(listChild.id.get -> listChild.asInstanceOf[Renderable].render)
      } else if (listChild.isInstanceOf[Container]) {
        if (listChild.id == None)
          listableChildren += listChild.asInstanceOf[Container].getModel
        else
          listableChildren += Map(listChild.id.get -> listChild.asInstanceOf[Container].getModel)
      } else if (listChild.isInstanceOf[Listable]) {
        val container = new Container(None)
        container.add(listChild)
        listableChildren += container.getModel        
      }

      map += listable.id.get -> listableChildren
    })
  }

  private def visitLocale(attachable: Attachable) {
    if (attachable.isInstanceOf[Container]) {
      val component = attachable.asInstanceOf[Container]
      if (locale != null)
        component.locale = locale
    }
  }

}