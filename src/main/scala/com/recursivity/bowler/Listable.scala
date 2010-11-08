package com.recursivity.bowler

import collection.mutable.MutableList

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 4:16:58 AM
 * To change this template use File | Settings | File Templates.
 */

trait Listable extends Attachable{
  def getList: List[Attachable]
}