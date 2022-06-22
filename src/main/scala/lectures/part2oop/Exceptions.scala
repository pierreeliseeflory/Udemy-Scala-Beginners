/*
 Copyright 2022 Pierre-Elisée Flory

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //   println(x.length) // crash (throws a NullPointerException)

  // 1. throwing exceptions
  //   throw new NullPointerException // an expression throwing an exception
//   val aWeirdValue: String =
//     throw new NullPointerException // the expression returns Nothing

  // a class is throwable if it extends from the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. catching exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you !")
    else 42

  val potentialFail =
    try {
      // code that might fail
      getInt(true)
    } catch {
      case e: RuntimeException =>
        println("Caught a Runtime exception")
      case e: NullPointerException =>
        println("Caught a Null Pointer exception")
    } finally {
      // code tha will get executed no matter what
      println("finally")
    }

  // Exceptions come from the JVM

  /*
  potentialFail is an AnyVal because Scala tries
  to unify Int and Unit

  finally is optional and doesn't affect the return type
  so only use it for side-effects
   */

  // 3. Define exceptions
  class MyException extends Exception
  val exception = new MyException
  //   throw exception // crash the program

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with a StackOverflowError
    3. PocketCalculator class
        - add(x, y)
        - substract(x, y)
        - multiply(x, y)
        - divide(x, y)

        Throw
         - OverflowException if add(x, y) exceeds Int.MAX_VALUE
         - UnderflowException if substrcat(x, y) exceeds Int.MIN_VALUE
         - MathCalculationException for division by 0
   */

  // 1.
  // val array = Array.ofDim[Int](Int.MaxValue) // OutOfMemoryError

  // 2.
  def add(n: Int): Int =
    if (n == 0) 0
    else add(n - 1) + n
  // add(100000) // StackOverflowError

  // 3.
  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  class PocketCalculator {
    def add(x: Int, y: Int): Int =
      if ((x > 0 && Int.MaxValue - x < y)) throw new OverflowException
      x + y
    def substract(x: Int, y: Int): Int =
      if ((x < 0 && x - Int.MinValue < y)) throw new UnderflowException
      x - y
    def multiply(x: Int, y: Int): Int =
      x * y
    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException
      x / y
  }

  val calc = new PocketCalculator

  println(calc.add(2, 3))
  println(calc.substract(10, 7))
  println(calc.multiply(2, 3))
  println(calc.divide(10, 2))

  // println(calc.add(2147483600, 48)) // OverflowException
  // println(calc.substract(-2147483600, 49)) // UnderflowException
  // println(calc.divide(10, 0)) // MathCalculationException
}
