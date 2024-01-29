package lectures.part2Opp

object Objects extends App {
  object Person{ // scala object is single ton
    // static class level
    val N_EYE = 2
    def canFly = true
    // factory method
    def apply (f : Person ,m : Person) : Person = new Person("Bob");
  }



  //single ton
  val mary = Person

  class Person(var name :String){
    // instance level
  }
  val john = Person
  println(Person.N_EYE)
  println(mary == john)
  val person1 = new Person("Mary")
  val person2 = new Person("John")
  val bobbie = Person.apply(person1,person2)
}
