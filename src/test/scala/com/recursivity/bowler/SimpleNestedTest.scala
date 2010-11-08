package com.recursivity.bowler

import org.scalatest.FunSuite
import stub.{SimpleComponent, NestedComponent}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 2:20:42 AM
 * To change this template use File | Settings | File Templates.
 */

class SimpleNestedTest extends FunSuite{
  test("simple nested component"){
    val template = new NestedComponent(None)
    template.add(new SimpleComponent(Some("nested")))
    println(template.render)
    assert(template.render.equals("This is nested: Hello world"))
  }
}