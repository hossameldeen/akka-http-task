//=====================================================================================================================
// General
scalaVersion := "2.11.8"

libraryDependencies += "com.typesafe.akka" %% "akka-http-experimental" % "2.4.9"

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.9"

libraryDependencies += "com.typesafe.akka" %% "akka-http-testkit" % "2.4.9"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

scalacOptions ++= Seq("-deprecation")  // to show the warnings
//=====================================================================================================================



//=====================================================================================================================
// For gen-sublime's plugin
import com.orrsella.sbtsublime.SublimePlugin.autoImport
  .{sublimeExternalSourceDirectoryName, sublimeExternalSourceDirectoryParent, sublimeTransitive}

sublimeTransitive := true

/*sublimeExternalSourceDirectoryName := "ext-lib-src-for-sublime"

sublimeExternalSourceDirectoryParent := new java.io.File(baseDirectory.value, "src/main/scala")

// don't compile the downloaded source files of the lib
(excludeFilter in unmanagedSources) :=(excludeFilter in unmanagedSources).value ||
                                       new SimpleFilter(file => file.equals(sublimeExternalSourceDirectoryName.value))*/
//=====================================================================================================================