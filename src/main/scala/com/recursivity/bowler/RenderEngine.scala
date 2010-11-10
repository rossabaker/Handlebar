package com.recursivity.bowler

import org.fusesource.scalate.TemplateEngine

/**
 * Singleton object to hold the Scalate Template engine
 */

object RenderEngine{
  val engine = new TemplateEngine

  def getEngine = engine
}