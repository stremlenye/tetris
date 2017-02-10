name := """tetris"""

version := "1.0"

scalaOrganization in ThisBuild := "org.typelevel"

scalaVersion := "2.12.1"

scalacOptions += "-Ypartial-unification" // enable fix for SI-2712
scalacOptions += "-Yliteral-types"       // enable SIP-23 implementation

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

