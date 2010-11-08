package com.recursivity.bowler

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 1:49:18 AM
 * To change this template use File | Settings | File Templates.
 */

trait Renderable extends Attachable{
  def render: String
}