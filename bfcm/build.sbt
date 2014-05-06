name := "bfcm"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "org.squeryl" %% "squeryl" % "0.9.5-6",
  "com.h2database" % "h2" % "1.2.127"
)     

play.Project.playScalaSettings
