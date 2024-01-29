package lectures.part2Opp

object Generics extends App {
  class MyList[A] {
    // A is generic type
    def add[B >: A](element:B):MyList[B] = ???
    /*
    A = cat
    B = Animal
    then returns list of animal
     */
  }
  class MyMap[Key,Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]
  // generic methods
  object MyList{
    def empty[A]:MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal
  // List[Cat] extends List[Animal] = Covariance
  class CovariantList[+A]
  val animal:Animal=new Cat
  val animalList :CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? => we return list of animals

  // Invariant list
  class invariantList[A]
  val invaliantAnimal:invariantList[Animal] = new invariantList[Animal]

  // no! contravariant list
  class ContravariantList[-A]
  val contravariantList:ContravariantList[Cat] = new ContravariantList[Animal]

  // bounded types allow u to use generic type of sub type n all
  class Cage[A <: Animal](animal:A) // accept parameter of subtype of Animal   < sub type , > super type
  val cage = new Cage(new Dog)

  // expand MyList to be generic

}
