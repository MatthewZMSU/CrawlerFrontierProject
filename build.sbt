name := """MusicService"""
organization := "MSU"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.10"

//libraryDependencies ++= Seq(
//  jdbc
//)
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
libraryDependencies ++= Seq(
  guice,
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0"
)
libraryDependencies ++= Seq("mysql" % "mysql-connector-java" % "8.0.32")

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "MSU.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "MSU.binders._"
