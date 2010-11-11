package com.recursivity.bowler

import org.scalatest.FunSuite
import stub.InvertedSection

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 10, 2010
 * Time: 11:03:37 PM
 * To change this template use File | Settings | File Templates.
 */

class ContainerTest extends FunSuite{

  test("test get"){
    val container = new Container(None)
    val renderable = new SimpleRenderable(Some("first"), {"other"})
    val r2 = new SimpleRenderable(Some("second"), {"other"})

    container.add(renderable)
    container.add(r2)

    assert(renderable == container.get("first").get)
    assert(r2 == container.get("second").get)
    assert(!container.get("otherRandomId").isInstanceOf[Some[Attachable]])

  }


  test("test non false value with inverted section"){
    val c = new InvertedSection
    val container = new Container(Some("repo"))
    container.add(new SimpleRenderable(Some("name"), {"hello"}))
    c.add(container)
    assert("<b>hello</b>".equals(c.render))
    //fail
  }

  test("test empty list, false, none with inverted section"){
    val c = new InvertedSection
    c.add(new Container(Some("repo")))

    assert("No repos :(".equals(c.render))

    val c2 = new InvertedSection
    assert("No repos :(".equals(c2.render))
  }
  
}