package com.recursivity.bowler

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 1:27:24 AM
 * To change this template use File | Settings | File Templates.
 */

class SimpleRenderable(componentId: Option[String], text: => String) extends Renderable{
  def render = text
  def id = componentId
}