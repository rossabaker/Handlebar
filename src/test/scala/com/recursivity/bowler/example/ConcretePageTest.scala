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


  test("test concrete page"){
    val page = new ConcretePage
    println(page.render)
  }

  test("concretePageWith Cache"){
    val page = new ConcretePage with Cacheable{
      def get: Option[String] = {
        try{
          return Some(SimpleCache.map("concretePage"))
        }catch{
          case e: NoSuchElementException => {
            return None
          }
        }
      }

      def put(rendered: String) = SimpleCache.map += "concretePage" -> rendered
    }

    var i = 0
    var total: Long = 0
    while(i < 1000){
      SimpleCache.map.remove("concretePage")
      val start = System.currentTimeMillis
      page.render
      total = total + (System.currentTimeMillis - start)
      i = i + 1
    }

    println("Average render time over 100x: " + (total / 100))

  }
}

object SimpleCache{
  val map = new HashMap[String, String]

}