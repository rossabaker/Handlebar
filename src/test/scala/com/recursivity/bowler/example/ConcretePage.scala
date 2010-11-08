package com.recursivity.bowler.example

import com.recursivity.bowler.{Attachable, SimpleRenderable}
import com.recursivity.commons.StringInputStreamReader

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:00:02 AM
 * To change this template use File | Settings | File Templates.
 */

class ConcretePage extends BasePage with StringInputStreamReader{
  def getBody(id: String): Attachable = {
    val panel = new ArticlePanel(Some("body"))
    panel.add(new SimpleRenderable(Some("title"), {"Are Serverside Web Frameworks Becoming Irrelevant?"}))

    panel.add(new SimpleRenderable(Some("body"), {
      this.load(this.getClass.getResourceAsStream("/com/recursivity/bowler/example/article.html"))
    }))
    
    return panel
  }

  def getHeader(id: String) = new HeaderPanel(Some(id))

  def getTitle(id: String) = new SimpleRenderable(Some(id), {"Some Page Title"})
}
