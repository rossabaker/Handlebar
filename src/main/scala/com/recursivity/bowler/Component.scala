package com.recursivity.bowler


import com.recursivity.commons.{ClasspathTextResource, ClasspathResourceResolver}
import org.fusesource.scalate.TemplateEngine
import org.fusesource.scalate.support.{FileResourceLoader, Resource}
import reflect.BeanProperty
import collection.mutable.{HashMap, MutableList}

/**
 * Created by IntelliJ IDEA.
 * User: wfaler
 * Date: Nov 7, 2010
 * Time: 12:12:02 AM
 * To change this template use File | Settings | File Templates.
 */

abstract class Component(componentId: Option[String]) extends Container(componentId) with Renderable{

  @BeanProperty
  var templateType = ".mustache"

  private var templateFileClass: Class[_] = null

  // this class needs some clean-up..
  private def resolveTemplateClass(cls: Class[_]): Class[_] ={
    if(templateFileClass != null)
      return templateFileClass
    try{
      val resource = new ClasspathTextResource(cls, templateType, locale)
      resource.load
      templateFileClass = cls
    }catch{
      case e: NullPointerException => {
        templateFileClass = resolveTemplateClass(cls.getSuperclass)
      }
    }
    return templateFileClass
  }


  def resourceLoader = new ClasspathTextResource(resolveTemplateClass(this.getClass), templateType, locale)
  def templateUri = ClasspathResourceResolver.getUri(resolveTemplateClass(this.getClass), templateType, locale)
  
  def render: String = {
    val engine = new TemplateEngine
    engine.resourceLoader = new FileResourceLoader {
      override def resource(uri: String): Option[Resource] = Some(Resource.fromText("Template" + templateUri, resourceLoader.load))
    }
    return engine.layout("Template" + templateUri, this.getModel)
  }

}