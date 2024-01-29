package lectures.part2Opp

object CaseClasses extends App {
    /*
      equal,hashCode,toString
    */
   case class Person(name:String,age:Int)
  // 1. class parameters are fields
  val jim = new Person("Jim",23)
  println(jim.name)

  //  2. sensible toString
  // println (instance) = toString method
  println(jim.toString)
  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim",23)
  println(jim == jim2)

  // 4. CC's have handy copy methods

  val jim3 = jim2.copy(age = 45)
  println(jim3)

  // 5. CC's have companion objects
  val thePerson = Person
  val mary = Person("Mary",23)

  // 6.CC's are serializable
  //Akka

  // 7.CC's have extractor patterns = can be used in PATTERN MATCHING

  case object Uk{
    def name:String ="The Uk of GB and NI"
  }
  /*
  expand my list - use case classes and case objects
   */
}
