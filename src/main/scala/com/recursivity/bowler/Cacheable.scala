package com.recursivity.bowler

/**
 * Trait to be mixed in if you want a Renderable to be cacheable.<br/>
 * Cacheable does not take into regard what Cache system you use, and furthermore it is expected to be able how to put- and get itself out of a cache.
 * For instance, for a keyed cache, it is expected that the put- and get functions can calculate their own cache keys.<br/>
 * Cache expiry is not a concern of the Cacheable trait, but is expected to be an external concern.<br/>
 */

trait Cacheable extends Renderable{
  def put(rendered: String)
  def get: Option[String]
}