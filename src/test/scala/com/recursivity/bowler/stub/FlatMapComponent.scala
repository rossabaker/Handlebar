package com.recursivity.bowler.stub

import com.recursivity.bowler._

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 11:07:27 PM
 * To change this template use File | Settings | File Templates.
 */

class FlatMapComponent extends Component(Some("hello")){

}


class ContainerListable extends Listable{
  def id = Some("list")

  def getList: List[Attachable] = {
    val c1 = new Container(None)
    c1.add(new SimpleRenderable(Some("firstName"), {"Wille"}))
    c1.add(new SimpleRenderable(Some("lastName"), {"Faler"}))

    val c2 = new Container(None)
    c2.add(new SimpleRenderable(Some("firstName"), {"John"}))
    c2.add(new SimpleRenderable(Some("lastName"), {"Doe"}))

    return List(c1, c2)
  }
}