package lectures.part3fp.HOF

/*
   1.Expand MyList
     - foreach method A => Unit
       [1,2,3].foreach(x => println(x))  will print each element on new line
     - sort function ((A,A) => Int) =>MyList
       [1,2,3].sort((x,y)=> y - x) .. [3,2,1]
     - zipWith (list,(A,A)=>B)=>MyList[B]
       [1,2,3].zipWith([4,5,6],(x*y) => [1*4,2*5,3*6] = [4,10,18]
     - fold(start)(function)=> a value
       [1,2,3].fold(0)(x+y) => 6 return sum of all values

     2.toCurry(f:(Int,Int)=>Int) => (Int => Int => Int)
       fromCurry (f:(Int => Int => Int)) => (Int,Int)=>Int

     3.compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
 */
abstract class MyListGeneric[+A] {
  def head: A

  def tail: MyListGeneric[A]

  def isEmptyObj: Boolean

  def addElement[B >: A](element: B): MyListGeneric[B]

  def printElements: String

  override def toString: String = "[ " + printElements + " ]"

  // higher order functions
  def map[B](transformer: A => B): MyListGeneric[B]

  def flatMap[B](transformer: A => MyListGeneric[B]): MyListGeneric[B]

  def filter(predicate: A => Boolean): MyListGeneric[A]

  // concatenation
  def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B]

  // hof's
  def foreach(f:A => Unit):Unit
  def sort(compare:(A,A) => Int):MyListGeneric[A]
  def zipWith [B,C] (List:MyListGeneric[B],zip:(A,B) => C):MyListGeneric[C]
  def fold[B](start:B)(operator:(B,A)=>B):B
}

case object EmptyObj extends MyListGeneric[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyListGeneric[Nothing] = throw new NoSuchElementException

  def isEmptyObj: Boolean = true

  def addElement[B >: Nothing](element: B): MyListGeneric[B] = new ConsObj(element, EmptyObj)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyListGeneric[B] = EmptyObj

  def flatMap[B](transformer: Nothing => MyListGeneric[B]): MyListGeneric[B] = EmptyObj

  def filter(predicate: Nothing => Boolean): MyListGeneric[Nothing] = EmptyObj

  def ++[B >: Nothing](list: MyListGeneric[B]): MyListGeneric[B] = list

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyListGeneric[Nothing] = EmptyObj

  override def zipWith[B, C](List: MyListGeneric[B], zip: (Nothing, B) => C): MyListGeneric[C] =
    if(!List.isEmptyObj) throw new RuntimeException("Lists do not have same lengths")
    else EmptyObj

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ConsObj[+A](h: A, t: MyListGeneric[A]) extends MyListGeneric[A] {
  def head: A = h

  def tail: MyListGeneric[A] = t

  def isEmptyObj: Boolean = false

  def addElement[B >: A](element: B): MyListGeneric[B] = new ConsObj(element, this)

  def printElements: String =
    if (t.isEmptyObj) s"$h"
    else s"$h" + " " + s"${t.printElements}"

  def filter(predicate: A => Boolean): MyListGeneric[A] = {
    if (predicate(h)) new ConsObj(h, t.filter(predicate))
    else t.filter((predicate))
  }

  def map[B](transformer: A => B): MyListGeneric[B] =
    new ConsObj(transformer(h), t.map(transformer))

  def ++[B >: A](list: MyListGeneric[B]): MyListGeneric[B] = new ConsObj[B](h, t ++ list)

  /*
    [1,2] ++ [3,4,5]
    = new Cons(1,[2] ++ [3,4,5])
    = new Cons(1,new Cons(2,Empty ++ [3,4,5]))
    = new Cons(1,new Cons(2,new Cons(3,new Cons(4,new Cons(5)))))
   */


  def flatMap[B](transformer: A => MyListGeneric[B]): MyListGeneric[B] =
    transformer(h) ++ t.flatMap(transformer)
  /*
    [1,2].flatMap(n => n+1)
    = [1,2] ++ [2].flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ Empty
    = [1,2,2,3]
   */
  // hofs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): MyListGeneric[A] = {
    def insert(x:A,sortedList:MyListGeneric[A]):MyListGeneric[A]=
      if(sortedList.isEmptyObj) new ConsObj(x,EmptyObj)
      else if(compare(x,sortedList.head) <= 0) new ConsObj(x,sortedList)
      else new ConsObj(sortedList.head,insert(x,sortedList.tail))

    val sorted_tail = t.sort(compare)
    insert(h,sorted_tail)
  }
  override def zipWith[B, C](List: MyListGeneric[B], zip: (A, B) => C): MyListGeneric[C] =
    if(List.isEmptyObj) throw new RuntimeException("Lists do not have same lengths")
    else ConsObj(zip(h,List.head),t.zipWith(List.tail,zip))

  /*
  - fold(start)(function)=> a value
       [1,2,3].fold(0)(x+y) => 6 return sum of all values
  */
  override def fold[B](start: B)(operator: (B, A) => B): B =
    val newStart=operator(start,h)
    t.fold(newStart)(operator)
  /*
    [1,2,3].fold(0)(+) =
     [2,3].fold(1)(+) =
      [3].fold(3)(+) =  2 is tail
      [].fold(6)(+)
      = 6
   */
}

/*
     2.toCurry(f:(Int,Int)=>Int) => (Int => Int => Int)
       fromCurry (f:(Int => Int => Int)) => (Int,Int)=>Int

     3.compose(f,g) => x => f(g(x))
       andThen(f,g) => x => g(f(x))
 */

def toCurry(f:(Int,Int) => Int):(Int => Int => Int) =
  x => y => f(x,y)

def fromCurry(f:(Int=>Int=>Int)):((Int,Int)=> Int) =
  (x,y) => f(x)(y)
def compose(f:Int=>Int,g:Int=>Int):Int=>Int=
  x => f(g(x))
def andThen(f:Int=>Int,g:Int=>Int):Int=>Int=
  x => g(f(x))
object HOFExercise extends App {




    val listOfIntegers: MyListGeneric[Int] = new ConsObj[Int](1, new ConsObj[Int](2, new ConsObj[Int](3, EmptyObj)))
    val anotherListOfIntegers: MyListGeneric[Int] = new ConsObj[Int](4, new ConsObj[Int](5, EmptyObj))
    val listOfStrings: MyListGeneric[String] = new ConsObj[String]("Hello", new ConsObj[String]("Scala", EmptyObj))
    println(listOfStrings.toString)
    println(listOfIntegers.toString)

    println(listOfIntegers.map(new Function1[Int, Int] {
      override def apply(elem: Int): Int = elem * 2
    }).toString)
    println(listOfIntegers.filter(new Function1[Int, Boolean] {
      override def apply(elem: Int): Boolean = elem % 2 == 0
    }).toString)

    println((listOfIntegers ++ anotherListOfIntegers).toString)

    println(listOfIntegers.flatMap(new Function1[Int, MyListGeneric[Int]] {
      override def apply(elem: Int): MyListGeneric[Int] = new ConsObj[Int](elem, new ConsObj[Int](elem + 1, EmptyObj))
    }).toString)

    listOfIntegers.foreach(println)

    println(listOfIntegers.sort( (x,y) => y - x))
    println(anotherListOfIntegers.zipWith(listOfStrings,_+ " "+_))
    println(listOfIntegers.fold(0)(_ + _))

    def superAdder2 : (Int => Int =>Int) = toCurry(_+_)
    val adder = superAdder2(4)
    println(adder(5))

    val adder2 = (x:Int) => x + 2
    val times2 = (x : Int) => x * 3

    val composed = compose(adder2,times2)
    val ordered = andThen(adder2,times2)

    println(composed(4))
    println(ordered(4))
}
