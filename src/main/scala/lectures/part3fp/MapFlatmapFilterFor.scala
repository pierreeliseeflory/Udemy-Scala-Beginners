package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  /*
    print all combinations between 2 lists
   */
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  val combine = (list: List[Char]) => (x: Int) => list.map("" + _ + x)
  println(numbers.flatMap(combine(chars)))
  val colors = List("black", "white")
  val combinations = numbers.flatMap(n =>
    chars.flatMap(c => colors.map(color => color + "." + c + "." + n))
  )
  println(combinations)

  // foreach
  // like map but taking a function returning () (Unit)
  list.foreach(println)

  // for-comprehensions
  // more readable map and flatMap combinations
  // allow filtering
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield color + "." + c + "." + n

  println(forCombinations)

  for {
    n <- numbers
  } println(n)
  // identical to foreach(println)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. Does MyList supports for comprehensions ?
      i.e supports map, flatMap, filter, foreach
    2. A small collection of at most ONE element - Maybe[+T] (either empty or one element of type T)
      - map, flatMap, filter
   */
}
