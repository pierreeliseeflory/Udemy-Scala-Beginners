package lectures.part3fp

object AnonymousFunctions extends App {
  //   val doubler = new Function1[Int, Int] {
  //     override def apply(x: Int) = x * 2
  //   }

  // anonymous function or lambda
  val doubler: Int => Int = (x: Int) => x * 2
  val doubler2: Int => Int = x => x * 2

  // multiple parmas in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no params
  val justDoSomething: () => Int = () => 3

  println(justDoSomething())
  // lambda unlike methods require to use parenthesis
  // this below is the instance of the function
  println(justDoSomething)

  // lambda with curly braces
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // More syntatcic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1

  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
    1. MyList: replace all Functionx calls with lambdas
    2. Rewrite the "special" adder as an anonymous one
   */
  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))
}
