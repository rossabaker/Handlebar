package com.recursivity.bowler

import org.scalatest.FunSuite
import stub._

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 12:42:02 AM
 * To change this template use File | Settings | File Templates.
 */

class SimpleComponentTest extends FunSuite{

  test("test simple template"){
    val template = new SimpleComponent(None)
    assert(template.render.equals("Hello world"))
  }

  test("simple nested component"){
    val template = new NestedComponent(None)
    template.add(new SimpleComponent(Some("nested")))
    assert(template.render.equals("This is nested: Hello world"))
  }

  test("test label"){
    val template = new NestedComponent(None)
    template.add(new SimpleRenderable(Some("nested"), {"this is a label"}))
    println(template.render)
    assert(template.render.equals("This is nested: this is a label"))
  }

  test("test update component label"){
    val template = new NestedComponent(None)
    template.add(new SimpleRenderable(Some("nested"), {"this is a label"}))
    println(template.render)
    assert(template.render.equals("This is nested: this is a label"))

    template.add(new SimpleRenderable(Some("nested"), {"this is another label"}))
    assert(template.render.equals("This is nested: this is another label"))
  }

  test("list with anonymous items only"){
    val tmpl = new ListComponent
    tmpl.add(new MyListable)
    assert(tmpl.render.equals("<ul><li>hello</li><li>hello2</li></ul>"))
  }

  test("list with named items only"){
    val tmpl = new NamedListComponent
    tmpl.add(new NamedListable)
    assert(tmpl.render.equals("<ul><li>wille</li><li>faler</li></ul>"))
  }

  test("list with named items only, test with anonymous elements"){
    val tmpl = new NamedListComponent
    tmpl.add(new MyListable)
    println(tmpl.render)
    assert(tmpl.render.equals("<ul><li></li><li></li></ul>"))
  }

  test("list with named items and anon list items only"){
    val tmpl = new ListComponent
    tmpl.add(new NamedListable)
    println(tmpl.render)
    assert(tmpl.render.equals("<ul><li></li><li></li></ul>"))
  }

  test("list with flat map"){
    val tmpl = new FlatMapComponent
    tmpl.add(new ContainerListable)
    println(tmpl.render)
    assert(tmpl.render.equals("<ul><li>Wille Faler</li><li>John Doe</li></ul>"))
  }

  test("list with nested lists"){
    val tmpl = new NestedListComponent
    tmpl.add(new NestedListable)
    println(tmpl.render)
    assert(tmpl.render.equals("<ul><li><ul><li>wille</li><li>faler</li></ul></li><li><ul><li>wille</li><li>faler</li></ul></li></ul>"))
    
  }

 /* test("list with nested lists in container"){

  }*/
}