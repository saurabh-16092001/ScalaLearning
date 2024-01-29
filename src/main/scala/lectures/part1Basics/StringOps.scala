package lectures.part1Basics

object StringOps extends App {
  val str:String = "Hello I'm learning Scala"

  println(str.charAt(6))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.split("").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.toLowerCase())

  val aNumberString = "123"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z')
  println(str.take(2))

  // scala specific:String interpolator

  // S-interpolators

  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"  // s is important
  val anothergGreeting = s"Hello, my name is $name and I am $age years old and I will be turning ${age + 1} year old"  // s is important


  // F-interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%1.2f burgers "

  println(myth)

  // raw-interpolator
  var escape = "this is a \n new line"
  println(raw"this is a \n new line")
  println(raw"$escape")
}
