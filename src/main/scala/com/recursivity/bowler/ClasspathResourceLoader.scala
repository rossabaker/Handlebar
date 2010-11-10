package com.recursivity.bowler

import com.recursivity.commons.{ClasspathResourceResolver, ClasspathTextResource}

/**
 * ResourceLoader that will load a resource from the Classpath with any localised version if applicable (falls back to default if no localised version exists).
 * <br/> Traverses the superclasses of the given context if no template is found with the associated context class.
 */

class ClasspathResourceLoader(context: Class[_], templateType: String, locale: String) extends ResourceLoader{
  private var template: String = null
  private var templateFileClass: Class[_] = resolveTemplateClass(context)

  private def resolveTemplateClass(cls: Class[_]): Class[_] ={
    if(templateFileClass != null)
      return templateFileClass
    try{
      val resource = new ClasspathTextResource(cls, templateType, locale)
      template = resource.load
      templateFileClass = cls
    }catch{
      case e: NullPointerException => {
        templateFileClass = resolveTemplateClass(cls.getSuperclass)
      }
    }
    return templateFileClass
  }

  /**
   * gets the template as a String
   */
  def getTemplate: String = {
    return template
  }

  /**
   * gets the URI of the template, as used by Scalate for compilation, template caching etc.
   */
  def getUri = "classpath://" + ClasspathResourceResolver.getUri(templateFileClass, templateType, locale)
}