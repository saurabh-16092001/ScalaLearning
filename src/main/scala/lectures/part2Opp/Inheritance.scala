package lectures.part2Opp

object Inheritance extends App {
  // single class inheritance

  class Animal{
    val creatureType = "Wild"
    protected def eat = println("asfsa")
  }
  class Cat extends Animal{
    def crunch ={
      eat
      println("Eating")
    }
  }
  val cat = new Cat
  cat.crunch

  // constructor
  class Person(name:String,age:Int){
    def this(name:String) = this(name,0)
  }

  class Adult(name:String,age:Int,idCard:String) extends Person(name,age)

  class Dog(override val creatureType :String) extends Animal{
//    override val creatureType = "Domestic"
    override def eat = println("dog eat")
  }
  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution (polymorphism)
  val unKnownAnimal : Animal = new Dog("ks")
  // to prevent overriding
  // use final keyword on member
  // use final on entire class
  // sealed class == extend classes in this file only but prevent extension in other files
}
