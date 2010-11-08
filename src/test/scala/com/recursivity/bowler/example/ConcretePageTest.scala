package com.recursivity.bowler.example

import org.scalatest.FunSuite

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:15:05 AM
 * To change this template use File | Settings | File Templates.
 */

class ConcretePageTest extends FunSuite{

  test("test concrete page"){
    val page = new ConcretePage
    println(page.render)
  }
}