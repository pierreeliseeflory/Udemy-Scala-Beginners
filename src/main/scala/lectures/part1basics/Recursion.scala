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

package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {
  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(
        "Computing facorial of " + n + " - I first need factorial of " + (n - 1)
      )
      val result = n * factorial(n - 1)
      println("Computed factorial of " + n)
      result
    }

  println(factorial(10))
  // println(factorial(10000))
  // Stack Overflow
  // recursion break when iterating too much which floods the stack

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    // the compiler checks that the following function is indeed
    // a tail recursion
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator)

    factHelper(n, 1)
  }

  // tail-recursion because the recursion call is the last expression
  // thus the current stack is replaced by the new stack

  println(anotherFactorial(10))
  // println(anotherFactorial(10000))

  // Replace loops by tail recursion

  // 1. Concatenate a string n times using tail recursion
  // 2. IsPrime function using tail recursion
  // 3. Fibonacci function using a tail recursion

  def concatenate(aString: String, n: Int): String = {
    @tailrec
    def concatenateAux(aString: String, n: Int, acc: String): String =
      if (n <= 1) acc
      else concatenateAux(aString, n - 1, acc + aString)

    concatenateAux(aString, n, aString)
  }

  println(concatenate("hello", 3))

  def isPrime(p: Int): Boolean = {
    @tailrec
    def isPrimeAux(p: Int, test: Int, bool: Boolean): Boolean =
      if !bool then false
      else if test == p / 2 then true
      else isPrimeAux(p, test + 1, bool && p % test != 0)

    isPrimeAux(p, 2, true)
  }

  println(isPrime(2003))

  def fibonacci(n: Int): Int = {
    @tailrec
    def fibonacciAux(i: Int, acc1: Int, acc2: Int): Int =
      if (i == n) acc2
      else fibonacciAux(i + 1, acc2, acc1 + acc2)

    fibonacciAux(1, 0, 1)
  }

  println(fibonacci(10))

}
