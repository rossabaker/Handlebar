package com.recursivity.bowler.example

import org.scalatest.FunSuite
import com.recursivity.bowler.Cacheable
import collection.mutable.HashMap

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 8, 2010
 * Time: 1:15:05 AM
 * To change this template use File | Settings | File Templates.
 */

class ConcretePageTest extends FunSuite{
  val map = new HashMap[String, String]

  test("test concrete page"){
    val page = new ConcretePage
    println(page.render)
  }

  test("concretePageWith Cache"){
    val page = new ConcretePage with Cacheable{
      def get: Option[String] = {
        try{
          return Some(map("concretePage"))
        }catch{
          case e: NoSuchElementException => {
            return None
          }
        }
      }

      def put(rendered: String) = map += "concretePage" -> rendered
    }

    var i = 0
    while(i < 40){
      if(i == 20)
        map.remove("concretePage")
      val start = System.currentTimeMillis
      page.renderCached
      println("render time: " + (System.currentTimeMillis - start))
      i = i + 1
    }    

  }
}