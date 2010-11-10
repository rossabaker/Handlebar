package com.recursivity.bowler.example

import com.recursivity.bowler.{SimpleRenderable}


/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:00:02 AM
 * To change this template use File | Settings | File Templates.
 */

class ConcretePage extends BasePage{
  def getBody(id: String) = new ArticlePanel(Some("body"))
    
  def getHeader(id: String) = new HeaderPanel(Some(id))

  def getTitle(id: String) = new SimpleRenderable(Some(id), {"Some Page Title"})
}
