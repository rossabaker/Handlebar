package com.recursivity.bowler

import collection.mutable.{MutableList, HashMap}
import reflect.BeanProperty

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 3:32:42 AM
 * To change this template use File | Settings | File Templates.
 */

class Container(componentId: Option[String]) extends Attachable {
  if (this.isInstanceOf[Listable])
    throw new IllegalStateException(getClass.getName + "is both a Container and Listable. A Container cannot be a Listable!")

  @BeanProperty
  var locale: String = null

  val children = new MutableList[Attachable]
  val childIndex = new HashMap[String, Int]

  def getChildren = children.toList

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

  def getModel: Map[String, Any] = {
    return getModel(children)

  }

  private def getModel(tree: MutableList[Attachable]): Map[String, Any] = {
    val map = new HashMap[String, Any]

    children.foreach(child => {
      visitLocale(child)

      if (child.isInstanceOf[Renderable])
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