package com.recursivity.bowler


/**
 * A trait that attaches itself to a list and returns the individual list items.
 * Though you can implement this trait directly, you may find ListModel to be a useful Convenience class for implementing this trait.
 */

trait Listable extends Attachable{
  def getList: List[Attachable]
}