package lectures.part2Opp

object AnonymousClasses extends App {
  abstract class Animal{
    def eat:Unit
  }
  // anonymous class
  val funnyAnimal:Animal = new Animal:
    override def eat: Unit = println("fasdfasdfsa")
  /*
    equivalent with
  class AnonymousClasses$$anon$1 extends Animal{
   override def eat: Unit = println("fasdfasdfsa")
   */
  println(funnyAnimal.getClass)
  class Person(name:String){
    def sayHI:Unit=println(s"Hi my name is $name,how can I help you?")
  }

  val jim = new Person("Jim"){
    override def sayHI:Unit=println(s"Hi my name is Jim ,how can I be of service?")
  }
  /*

   */
}
