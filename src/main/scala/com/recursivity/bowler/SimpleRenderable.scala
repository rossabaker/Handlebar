package com.recursivity.bowler

/**
 * A very simple Renderable that takes an id, and any function returning a String.
 * Renders the result of the function once render is called.
 */

class SimpleRenderable(componentId: Option[String], text: => String) extends Renderable{
  def render = text
  def id = componentId
}