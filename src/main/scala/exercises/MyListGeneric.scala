package exercises

abstract class MyListGeneric[+A] {
  def head:A
  def tail:MyListGeneric[A]
  def isEmptyObj:Boolean
  def addElement[B >: A](element:B):MyListGeneric[B]
  def printElements:String
  override def toString:String="[ "+ printElements + " ]"

  def map[B](transformer: MyTransformer[A,B]):MyListGeneric[B]
  def flatMap[B](transformer: MyTransformer[A,MyListGeneric[B]]):MyListGeneric[B]
  def filter(predicate: MyPredicate[A]):MyListGeneric[A]
  // concatenation
  def ++[B >: A ](list:MyListGeneric[B]) :MyListGeneric[B]
}

case object EmptyObj extends MyListGeneric[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException

  def isEmptyObj: Boolean = true

  def addElement[B >: Nothing](element: B): MyListGeneric[B] = new ConsObj(element,EmptyObj)

  def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing, B]): MyListGeneric[B] = EmptyObj

  def flatMap[B](transformer: MyTransformer[Nothing, MyListGeneric[B]]): MyListGeneric[B] =EmptyObj

  def filter(predicate: MyPredicate[Nothing]): MyListGeneric[Nothing] = EmptyObj
  def ++[B >: Nothing ](list:MyListGeneric[B]) :MyListGeneric[B] = list
}

case class ConsObj[+A](h:A,t:MyListGeneric[A]) extends MyListGeneric[A] {
  def head: A = h

  def tail: MyListGeneric[A] = t

  def isEmptyObj: Boolean = false

  def addElement [B >: A](element: B): MyListGeneric[B] = new ConsObj(element, this)

  def printElements: String =
    if (t.isEmptyObj) s"$h"
    else s"$h" + " " + s"${t.printElements}"

  def filter(predicate:MyPredicate[A]) :MyListGeneric[A] = {
    if(predicate.test(h)) new ConsObj(h,t.filter(predicate))
    else t.filter((predicate))
  }
  def map[B](transformer: MyTransformer[A,B]):MyListGeneric[B] =
    new ConsObj(transformer.transform(h),t.map(transformer))

  def ++[B >: A ](list:MyListGeneric[B]) :MyListGeneric[B] = new ConsObj[B](h,t ++ list)

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1,[2] ++ [3,4,5])
    = new Cons(1,new Cons(2,Empty ++ [3,4,5]))
    = new Cons(1,new Cons(2,new Cons(3,new Cons(4,new Cons(5)))))
   */


  def flatMap[B](transformer: MyTransformer[A,MyListGeneric[B]]):MyListGeneric[B]=
    transformer.transform(h) ++ t.flatMap(transformer)
  /*
    [1,2].flatMap(n => n+1)
    = [1,2] ++ [2].flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
}

trait MyPredicate[-T]{  // T => Boolean
  def test(elem:T):Boolean
}

trait MyTransformer[-A,B]{ // A => B
  def transform(elem : A): B
}

object MyListGenericGeneric extends App{
  val listOfIntegers:MyListGeneric[Int] = new ConsObj[Int](1,new ConsObj[Int](2,new ConsObj[Int](3,EmptyObj)))
  val anotherListOfIntegers:MyListGeneric[Int] = new ConsObj[Int](4,new ConsObj[Int](5,EmptyObj))
  val listOfStrings:MyListGeneric[String] = new ConsObj[String]("Hello",new ConsObj[String]("Scala",EmptyObj))
  println(listOfStrings.toString)
  println(listOfIntegers.toString)

  println(listOfIntegers.map(new MyTransformer[Int,Int]{
    override def transform(elem: Int): Int = elem * 2
  }).toString)
  println(listOfIntegers.filter(new MyPredicate[Int]{
    override def test(elem:Int):Boolean = elem % 2 == 0
  }).toString)

  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println(listOfIntegers.flatMap(new MyTransformer[Int,MyListGeneric[Int]]{
    override def transform(elem: Int): MyListGeneric[Int] = new ConsObj[Int](elem,new ConsObj[Int](elem+1,EmptyObj))
  }).toString)
}