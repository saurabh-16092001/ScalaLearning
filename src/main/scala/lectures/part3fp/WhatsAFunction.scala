package lectures.part3fp

object WhatsAFunction extends App {
  //DREAM: use functions as first class elements
  // problem : oop
  val doubler = new MyFunction[Int,Int]{
    override def apply(element :Int ):Int = element * 2
  }
  println(doubler(3))

  val stringToIntConverter = new Function[String,Int]{
    override def apply(string:String):Int=string.toInt
  }
  println(stringToIntConverter("4") + 5)

  val adder = new Function2[Int,Int,Int]{
    override def apply(a:Int,b:Int):Int = a+b
  }
  // Function types Function2[A,B,R] == (A,B) => R
  // ALL SCALA FUNCTIONS ARE OBJECTS

  /*
    1. function which takes 2 strings and concatenates them
    2.  transform the MyPredicate and MyTransformer into function types
    3.  define the function which takes an int and returns another function which takes an int and returns an int
        - what's the type of function
        - how to do it
   */
  val stringConcate = new Function2[String,String,String]{
    override def apply(v1: String, v2: String): String = v1+v2
  }
//  def concatenatir:(String,String)=>String = new Function2[String,String,String]{
//    override def apply(v1: String, v2: String): String = v1 + v2
//  }
  // Function1[Int,Function1[Int,Int]]
  val superAdder:Function1[Int,Function1[Int,Int]] = new Function1[Int,Function1[Int,Int]]{
  override def apply(v1: Int): Function1[Int,Int] = new Function1[Int,Int]{
    override def apply(v2:Int):Int= v1 + v2
  }
}
  val adder3 = superAdder(4)
  println(adder3(3))
  println(superAdder(3)(4)) // curried function

  println(stringConcate("Add","Me"))
}

trait MyFunction[A,B]{
  def apply(element:A):B
}