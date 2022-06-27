package lectures.part3fp

object HOFsCurries extends App {
  // val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) =
  // ??? // correct but complicated
  // takes an int and a function and returns a function

  // a function taking a function as parameter or returning one is called a
  // Higher Order Function

  // a function that applies n times another function on a value
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x)))

  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 0))

  // Better
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x // the identity function
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(0))

  // curried functions
  val superAdder: Int => Int => Int = (x: Int) => (y: Int) => x + y
  // the arrow symbol is right-associative
  // ie : x => y => z == x => (y => z)
  val add3 = superAdder(3) // y => 3 + y
  println(add3(0))
  println(superAdder(3)(10))

  // functions with multiple paramete lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)
  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
    1. Expand MyList
      - foreach method A => Unit, apply the side-effect to every element
        [1, 2, 3].foreach(x => println(x))
      - sort function ((A, A) => Int) => MyList (take an ordering that returns positive if one is bigger or negative otherwise)
        [1, 2, 3].sort((x, y) => y - x) => [3, 2, 1]
      - zipWith (MyList, (A, A) => B) => MyList[B]
        [1, 2, 3].zipWith([4, 5, 6], x * y) => [1*4, 2*5, 3*6] ([4, 10, 18])
      - fold(start)(function) => a value // do element-wise recursive operation with a starting value
        [1, 2, 3].fold(0)(x+y) = 6

      2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
        fromCurry(f: (Int => Int => Int)) => ((Int, Int) => Int)

      3. compose(f, g) => x => f(g(x))
         andThen(f, g) => x => g(f(x))
   */
}
