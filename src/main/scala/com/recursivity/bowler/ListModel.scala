package com.recursivity.bowler

import collection.mutable.MutableList

/**
 * Convenience implementation of Listable.
 */

abstract class ListModel[T](componentId: Option[String], modelList: => List[T]) extends Listable{
  def id = componentId

  def getList: List[Attachable] = {
    val list = modelList
    val containers = new MutableList[Container]

    list.foreach(f => {
      val container = new Container(None)
      populateItem(container, f)
      containers += container

    })
    return containers.toList
  }

  /**
   * Populates one list item/row, takes the item container together with the modelItem.
   * To populate the row, add Attachables to the provided Container.
   */
  def populateItem(container: Container, modelItem: T)

}