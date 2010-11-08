import sbt._

class BowlerProject(info: ProjectInfo) extends DefaultProject(info){	
	
	val scalatest = "org.scalatest" % "scalatest" %
	    "1.2" % "test"
	val commons = "com.recursivity" % "recursivity-commons_2.8.0" % "0.1"
	val scalate = "org.fusesource.bowler" % "bowler-core" % "1.3"

  val scalaCompiler = "org.scala-lang" % "scala-compiler" % "2.8.0"

  val sfl4jnop = "org.slf4j" % "slf4j-nop" % "1.6.0" % "runtime"
  val sfl4japi = "org.slf4j" % "slf4j-api" % "1.6.0"

	
	val scalateRepo = "bowler repo" at "http://repo.fusesource.com/nexus/content/repositories/public/"
	
}