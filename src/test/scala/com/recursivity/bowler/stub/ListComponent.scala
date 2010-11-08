package com.recursivity.bowler.stub

import com.recursivity.bowler.{SimpleRenderable, Listable, Component}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 10:34:25 PM
 * To change this template use File | Settings | File Templates.
 */

class ListComponent extends Component(Some("hello")){

}


class MyListable extends Listable{
  def id = Some("list")

  def getList = List(new SimpleRenderable(None, {"hello"}),new SimpleRenderable(None, {"hello2"}))
}