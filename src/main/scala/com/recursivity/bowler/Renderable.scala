package com.recursivity.bowler

/**
 * An attachable that knows how to render itself
 */

trait Renderable extends Attachable{
  def render: String
}