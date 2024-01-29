package lectures.part2Opp

object AbstractDataTypes extends App {
  // abstract
  abstract class Animal{
    val creatureType:String
    def eat:Unit
  }
  class Dog extends Animal{
    override val creatureType: String = "wild"
    override def eat: Unit = println("crunch")
  }
  // traits
  trait coldBlooded
   trait Carnivore{
    def eat(animal:Animal):Unit
  }
  class Croc extends Animal with Carnivore with coldBlooded {
    val creatureType:String ="Croc"

    override def eat: Unit = println("crunch")

    override def eat(animal: Animal): Unit = println(s"I'm $creatureType eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Croc
  croc.eat(dog)

  // abstract vs traits
  // can have both abstract n non abstract members same for traits
  // 1. traits do not have constructor parameters
  // 2. multiple traits may be inherited by same class
  // 3. traits are behaviour
}
