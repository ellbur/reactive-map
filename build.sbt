
name := "reactive-map"

organization := "com.github.ellbur"

scalaVersion := "2.10.1"

scalaSource in Compile <<= baseDirectory(_ / "src")

libraryDependencies ++= Seq(
    "com.github.ellbur" %% "dependent-map" % "1.0-SNAPSHOT",
    "com.github.ellbur" %% "dependent-types" % "1.0-SNAPSHOT",
    "cc.co.scala-reactive" %% "reactive-core" % "0.3.0"
)

