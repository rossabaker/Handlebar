package com.recursivity.bowler.example

import com.recursivity.commons.StringInputStreamReader
import com.recursivity.bowler.{Cacheable, SimpleRenderable, Component}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:03:47 AM
 * To change this template use File | Settings | File Templates.
 */

class ArticlePanel(id: Option[String]) extends Component(id) with StringInputStreamReader{
  add(new SimpleRenderable(Some("title"), {"Are Serverside Web Frameworks Becoming Irrelevant?"}))

  add(new SimpleRenderable(Some("body"), {getArticle}))


  def getArticle: String = {
    try{
      return SimpleCache.map("bodyArticle")
    }catch{
      case e: NoSuchElementException => {
        println("cache miss!")
        val result = this.load(this.getClass.getResourceAsStream("/com/recursivity/bowler/example/article.html"))
        SimpleCache.map += "bodyArticle" -> result
        return result
      }
    }
  }

}