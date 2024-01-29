package lectures.part2Opp

import playground.{Demo =>Princess, PrinceCharming} // alias


object PackaginAndImports extends App{
  val writer = new Writer("Danial","Rock",2018) // package accessible by their name
  // import
  val demoPackage = new Princess  // fully qualified name -> playground.Demo

  // packages are in hierarchy
  // matching folder structure

  // package object
  val price = new PrinceCharming
  // default import
  // java lang - String,Object,Exception
  // scala - Int,Nothing
}
