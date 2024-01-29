package lectures.part3fp.MapFilterFlatmapFor

object MapFilterFlatmapFor extends App {

  val list = List(1,2,3)
  println(list.head)
  println(list.tail)
  // map
  println(list.map(_+1))
  println(list.map(_+" is a number"))
  // filter

  println( list.filter(x => x % 2 ==0))
  println( list.filter(_ % 2 ==0))
  // flatmap
  val toPair= ( x :Int) =>List (x,x+1)

  println(list.flatMap(toPair))

  val numbers=List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors=List("White","black")

  val combinations=numbers.flatMap(n => chars.map(c => ""+c+n))
  val combinations1=numbers.flatMap(n => chars.flatMap(c => colors.map(color => ""+c+n+"-"+color)))
  println(combinations)
  println(combinations1)

  //foreach
  list.foreach(println)

    // for-comprehensions
    val forCombinations=for{
      color <- colors
      n <- numbers if(n%2==0)  // guard numbers.filter(_%2==0).flatMap(n => chars.flatMap(c => colors.map(color => ""+c+n+"-"+color)))
      c <- chars
    }yield ""+c+n+"-"+color

  println(forCombinations)

  /*
    1.MyList supports for comprehensions ?
    2.A small collection of at most ONE element - Maybe[+T]
        - map,Flatmap,filter
   */
}
