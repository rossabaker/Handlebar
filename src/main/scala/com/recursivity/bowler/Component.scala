package com.recursivity.bowler


import org.fusesource.scalate.support.{FileResourceLoader, Resource}
import reflect.BeanProperty
import org.fusesource.scalate.{DefaultRenderContext}
import java.io.{PrintWriter, Writer}

/**
 * A component with associated markup, or with a super class with associated template.<br/>
 * Knows how to render itself with the help of a ResourceLoader.
 */

abstract class Component(componentId: Option[String]) extends Container(componentId) with Renderable{

  @BeanProperty
  var templateType = ".mustache"


  /**
   * gets the ResourceLoader for this Component. Default is ClasspathResourceLoader. May be overriden to return any other ResourceLoader.
   */
  def resourceLoader: ResourceLoader = new ClasspathResourceLoader(this.getClass, templateType, locale)


  /**
   *  Renders the Component.
   */
  def renderTo(writer: Writer): Unit = {

    val uri = resourceLoader.getUri
    val model = this.getModel
    val engine = RenderEngine.getEngine
    engine.resourceLoader = new FileResourceLoader {
      override def resource(uri: String): Option[Resource] = Some(Resource.fromText(uri, resourceLoader.getTemplate))
    }
    val pw = new PrintWriter(writer)
    val context = new DefaultRenderContext(uri, engine, pw)

    context.render(uri, model)
  }

}
