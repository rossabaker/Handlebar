package com.recursivity.bowler


/**
 * A trait that attaches itself to a list and returns the individual list items.
 */

trait Listable extends Attachable{
  def getList: List[Attachable]
}