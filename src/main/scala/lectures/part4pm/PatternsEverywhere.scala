package lectures.part4pm

object PatternsEverywhere extends App {
  // 1
  try {
    // code
  } catch {
    case e: RuntimeException       => "runtime"
    case npe: NullPointerException => "npe"
    case _                         => "something else"
  }

  // cathes are actually matches
  /*
        try {
            // code
            } catch (e) {
                e match {
                    case e: RuntimeException => "runtime"
                    case npe: NullPointerException => "npe"
                    case _ => "something else"
                }
            }
   */

  // 2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // also based on pattern matching
  } yield 10 * x

  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples // decomposed thanks to pattern matching
  } yield first * second
  // same for case classes, :: operators, ...

  // 3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple // multiple assignment through pattern matching
  println(a)

  var head :: tail = list
  println(head)
  println(tail)

  // 4
  // partial functions
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even"
    case 1               => "the one"
    case _               => "something else"
  }

  val mappedList2 = list.map { x =>
    x match {
      case v if v % 2 == 0 => v + " is even"
      case 1               => "the one"
      case _               => "something else"
    }
  }
  println(mappedList)
}
