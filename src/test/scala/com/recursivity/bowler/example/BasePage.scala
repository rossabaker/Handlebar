package com.recursivity.bowler.example

import com.recursivity.bowler.{Attachable, Renderable, Component}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 12:56:11 AM
 * To change this template use File | Settings | File Templates.
 */

abstract class BasePage extends Component(None){
  add(getTitle("title"))
  add(getHeader("header"))
  add(getBody("body"))


  def getTitle(id: String): Renderable

  def getHeader(id: String): Attachable
  def getBody(id: String): Attachable
}