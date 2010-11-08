package com.recursivity.bowler.stub

import com.recursivity.bowler.{Listable, Component}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 11:20:43 PM
 * To change this template use File | Settings | File Templates.
 */

class NestedListComponent extends Component(Some("hello")){

}

class NestedListable extends Listable{
  def id = Some("list")

  def getList = List(new NamedListable, new NamedListable)
}
