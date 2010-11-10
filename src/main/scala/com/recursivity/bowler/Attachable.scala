package com.recursivity.bowler

/**
 * Root trait for Bowler, depicts something that can attach itself to a template id/key
 */

trait Attachable{
  def id: Option[String]
}