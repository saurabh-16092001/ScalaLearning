package lectures.part2Opp

import scala.language.postfixOps

object MethodNotation extends App {

  class Person(val name: String, favMovie: String,val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favMovie
    def + (person: Person):String = s"${this.name} is haning out with ${person.name}"
    def +(nickName: String): Person = new Person(s"$name ($nickName)",favMovie)
    def unary_! : String = "This is exclamation"
    def unary_+ : Person = new Person(name, favMovie, age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi my name is $name and my favourite movie is ${favMovie}"
    def apply(n:Int) : String = s"$name watch favourite movie $favMovie $n times"
    def learns(sub:String):String = s"$name learning $sub"
    def learnsScala = this learns("scala")

  }

  val mary = new Person("Mary", "inception")
  println(mary.likes("inception"))
  println(mary likes "inception") // inflix notation only works with method with one parameter(Syntactic sugar)

  //"operators" in scala

  val tom = new Person("Tom", "fight club")
  //  println(mary + tom)
  println(1. + (2))
  // ALL OPERATORS ARE METHODS
  // Akka actos have ! ?

  // prefix notation
  val x = -1 // is equivalant to 1.unary_
  val y = 1.unary_-
  // unary_prefix only works with  - + ~ !
  println(!mary)
  println(mary.unary_!)

  // postfix notation only for methods without parameter
  println(mary.isAlive)
  println(mary isAlive)

  println(mary.apply())
  println(mary()) //equivalent

  println(+mary)

  println(mary + "meli")


  println((mary + "rockstar")())
  println((+mary).age)
  println(mary learnsScala)
}
