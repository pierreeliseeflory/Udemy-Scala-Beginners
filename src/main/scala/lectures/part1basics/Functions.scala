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

object Functions extends App {
  def aFunction(a: String, b: Int): String =
    a + " " + b

  // The compiler can also infer the return type of functions

  println(aFunction("hello", 3))

  def aParameterlessFunction: Int = 42

  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String =
    if (n == 1) aString else aString + aRepeatedFunction(aString, n - 1)

  println(aRepeatedFunction("hello", 3))
  // In Scala, replace loops by recursion

  // A recursive function require the return type to be defined

  def aFunctionWithSideEffect(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }

  // Exercises
  // 1. A greeting function (namge, age) => "Hi, my name is ... and I am ... years old."

  def greeting(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old."

  println(greeting("sam", 11))

  // 2. Factorial function

  def factorial(n: Int): Int =
    if (n <= 0) 1 else n * factorial(n - 1)

  println(factorial(3))

  // 3. Fibonacci function

  def fibonacci(n: Int): Int =
    if (n == 1 || n == 2) 1 else fibonacci(n - 1) + fibonacci(n - 2)

  println(fibonacci(8))

  // 4. Test if a number is prime

  def isPrime(n: Int): Boolean = {
    def isPrimeAux(i: Int): Boolean =
      if (i > n / 2) false else (n % i == 0) || isPrimeAux(i + 1)

    !isPrimeAux(2)
  }
  println(isPrime(17 * 37))
  println(isPrime(2003))
}
