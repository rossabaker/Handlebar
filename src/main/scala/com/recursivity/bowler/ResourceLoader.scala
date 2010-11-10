package com.recursivity.bowler

/**
 * Loads a template resource for a Component, such as the template itself and it's URI.
 */

trait ResourceLoader{

  def getUri: String

  def getTemplate: String
}