package lectures.part3fp

object WhatsAFunction extends App {
  // use functions as first class elements
  // OOP: everything is an object, i.e an instance of some class
  // FP: use a function type
  // Scala: All functions are objects

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int) = element * 2
  }

  println(doubler(2))

  // In Scala, basic function types are predefined
  // function types = FunctionN[..., R]
  // with N from 0 to 22 parameters
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  // val adder: Function2[Int, Int, Int]
  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  /*
    1. a function which takes 2 strings and concatenates them
    2. transform the MyPredicate and MyTransformer into function types
    3. define a function taking an Int and returns another function taking an Int and returning an Int
   */

  val concat: (String, String) => String =
    new Function2[String, String, String] {
      override def apply(string1: String, string2: String) = string1 + string2
    }

  println(concat("hello ", "world"))

  val nestedFunction = new Function1[Int, (Int => Int)] {
    override def apply(input: Int) = new Function1[Int, Int] {
      override def apply(input2: Int) = input + input2
    }
  }

  val nestedAdder = nestedFunction(3)

  println(nestedAdder(4))

  val nestedAnonymousFunction: (Int) => (Int => Int) = (x: Int) =>
    val insideFunction: (Int) => Int = _ + x
    insideFunction

  val nestedAnonymousAdder = nestedAnonymousFunction(3)
  println(nestedAdder(4))
}

trait Action[A, B] {
  def execute(element: A): B = ???
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
