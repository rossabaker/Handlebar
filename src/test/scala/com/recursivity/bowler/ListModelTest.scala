package com.recursivity.bowler

import org.scalatest.FunSuite
import stub.ListModelComponent

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 10, 2010
 * Time: 10:55:38 PM
 * To change this template use File | Settings | File Templates.
 */

class ListModelTest extends FunSuite{

  test("test ListModel"){
    val parent = new ListModelComponent
    parent.add(new ListModel[Tuple2[String, String]](Some("list"),{getList}){
      def populateItem(container: Container, listItem: (String, String)) = {
        container.add(new SimpleRenderable(Some("firstName"), {listItem._1}))
        container.add(new SimpleRenderable(Some("lastName"), {listItem._2}))
      }
    })

    assert(parent.render.equals("<ul><li>Wille Faler</li><li>John Doe</li><li>Jane Doe</li></ul>"))
  }


  def getList = List(("Wille", "Faler"), ("John", "Doe"), ("Jane", "Doe"))
}