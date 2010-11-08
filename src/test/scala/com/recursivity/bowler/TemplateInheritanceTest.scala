package com.recursivity.bowler

import org.scalatest.FunSuite
import stub.{TwiceInheritedComponent, InheritedComponent}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 12:11:49 AM
 * To change this template use File | Settings | File Templates.
 */

class TemplateInheritanceTest extends FunSuite{

  test("test simple inheritance"){
    val comp = new InheritedComponent(None)
    assert(comp.render.equals("Hello world"))
  }

  test("multiple levels of inheritance"){
    val comp = new TwiceInheritedComponent(None)
    assert(comp.render.equals("Hello world"))
  }
}