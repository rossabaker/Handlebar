package com.recursivity.bowler.stub

import com.recursivity.bowler.{SimpleRenderable, Listable, Component}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 10:42:55 PM
 * To change this template use File | Settings | File Templates.
 */

class NamedListComponent extends Component(Some("hello")){

}


class NamedListable extends Listable{
  def id = Some("list")

  def getList = List(new SimpleRenderable(Some("name"), {"wille"}),new SimpleRenderable(Some("name"), {"faler"}))
}