package com.recursivity.bowler.example

import com.recursivity.bowler._

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:19:49 AM
 * To change this template use File | Settings | File Templates.
 */

class HeaderPanel(id: Option[String]) extends Component(id){
  add(new SearchEngineList)

}


class SearchEngineList extends Listable{
  def id = Some("list")

  def getList: List[Attachable] = {
    val firstLink = new Container(None)
    firstLink.add(new SimpleRenderable(Some("href"), {"http://www.google.com"}))
    firstLink.add(new SimpleRenderable(Some("linkName"), {"Google"}))

    val secondLink = new Container(None)
    secondLink.add(new SimpleRenderable(Some("href"), {"http://www.bing.com"}))
    secondLink.add(new SimpleRenderable(Some("linkName"), {"Bing"}))

    val thirdLink = new Container(None)
    thirdLink.add(new SimpleRenderable(Some("href"), {"http://www.yahoo.com"}))
    thirdLink.add(new SimpleRenderable(Some("linkName"), {"Yahoo!"}))  

    return List(firstLink, secondLink, thirdLink)
  }
}