# Bowler - Composable Scala Templating The Wicket Way
This project is built on top of [Scalate](http://scalate.fusesource.org/) and aims to bring some of the benefits of [Apache Wicket](http://wicket.apache.org) style composability and inheritance of templates to the Scala and Scalate world.

You can build the project with [Simple Build Tool ("sbt")](), you will also need to pull down and build [Recursivity Commons](https://github.com/wfaler/recursivity-commons) first, which is a sister project with various utilities that Bowler uses. You will also need to add the scala-compiler.jar into the lib folder of the project.

At the moment, Bowler has been tested with [Mustache templates]() in Scalate only, but there is no reason to believe it shouldn't work with any other Scalate supported template style.

## Bowler - the name
Bowler is called Bowler as a little nod of appreciation as to where it owes it's lineage to: A bowler is the guy in Cricket throwing the ball at the Wicket.

## Features
*	Templating the Wicket Way: extend your class with the "Component"-class, and add a .mustache template with the same name and packaging as your class in the classpath.
*	Template inheritance: if your Component doesn't have it's own template, Bowler will traverse the class-hierarchy upwards to find the template of any super-class.
*	Composability and inheritance: the main reason for this framework. You can compose and inherit when you write your code, why shouldn't it apply to templates? Composability in my opinion is far superior to the traditional approach of "snippets" and includes.
*	Localisation of templates. For instance, add a MyComponent_se.mustache template next to your default template for a Swedish localised template version.
*	Support for all Mustache constructs with Wicket-like abstractions with a root trait of "Attachable", for those familiar with Wicket, "Listable" is similar to Wicket ListViews or RepeatingViews. Renderable is for classes that know how to render themselves (Component implements this trait). The Container class is for classes that can contain other Attachable's.

## Examples
The [Examples test source folder](https://github.com/wfaler/Bowler/tree/master/src/test/scala/com/recursivity/bowler/example) and the [Examples test resource folders](https://github.com/wfaler/Bowler/tree/master/src/test/resources/com/recursivity/bowler/example/) are the best way to get an idea (they need to be viewed together to make sense).
Also, the best class to start looking at poking is probably the [ConcretePage.scala](https://github.com/wfaler/Bowler/blob/master/src/test/scala/com/recursivity/bowler/example/ConcretePage.scala) example class, which should look very, very familiar for anyone familiar with Apache Wicket.

## What about Wicket?
I'm not looking to replace Wicket with this. I still love Wicket and will likely continue to be an avid Wicket user and proponent in the future.
Bowler is not a full-fledged web framework by any means, and with almost 100% certainty never will be. It is simply a stab at bringing the templating style we love from Wicket to the templating world outside of the Wicket Web framework.
Some may use it for plain templating, such as e-mail templates etc, others may use it to generate static html-pages, yet others may integrate it into their web frameworks. Only time will tell.