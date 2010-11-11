package com.recursivity.bowler

import java.io.{StringWriter, Writer}

/**
 * An attachable that knows how to render itself
 */

trait Renderable extends Attachable{
  def render: String = {
    val w = new StringWriter
    renderTo(w)
    w.toString
  }

  def renderTo(writer: Writer): Unit
}
