package com.recursivity.bowler


import org.fusesource.scalate.support.{FileResourceLoader, Resource}
import reflect.BeanProperty
import org.fusesource.scalate.{DefaultRenderContext}
import java.io.{PrintWriter, StringWriter}

/**
 * A component with associated markup, or with a super class with associated template.<br/>
 * Knows how to render itself with the help of a ResourceLoader, and to Cache itself and retrieve itself from cache if it is an instance of Cacheable.
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
   * Gets the result from Cache if the Component is Cacheable AND there is a Cache hit.
   * Puts the result into Cache IF the Component is Cacheable AND there was no cache hit.
   */
  def render: String = {
    if(this.isInstanceOf[Cacheable]){
      val cacheable = this.asInstanceOf[Cacheable]
      val option = cacheable.get
      if(option.isInstanceOf[Some[String]])
        return option.get
    }

    val uri = resourceLoader.getUri
    val model = this.getModel
    val engine = RenderEngine.getEngine
    engine.resourceLoader = new FileResourceLoader {
      override def resource(uri: String): Option[Resource] = Some(Resource.fromText(uri, resourceLoader.getTemplate))
    }
    val writer = new StringWriter
    val pw = new PrintWriter(writer)
    val context = new DefaultRenderContext(uri, engine, pw)

    context.render(uri, model)
    val result = writer.toString

    if(this.isInstanceOf[Cacheable]){
      val cacheable = this.asInstanceOf[Cacheable]
      cacheable.put(result)
    }
    return result
  }

}