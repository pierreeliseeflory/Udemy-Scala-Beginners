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

import scala.language.postfixOps

object MethodNotations extends App {
  class Person(val name: String, val favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String =
      s"${this.name} hangs out with ${person.name}"

    def +(person: Person): String =
      s"${this.name} hangs out with ${person.name}"

    def +(aString: String): Person =
      new Person(s"${this.name} $aString", this.favoriteMovie)

    def unary_! = s"hi $name !"

    def unary_+ =
      new Person(this.name, this.favoriteMovie, this.age + 1)

    def isAlive: Boolean = true

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(viewCount: Int): String =
      s"$name wathed $favoriteMovie $viewCount times."

    def learns(aString: String): String = s"${this.name} learns $aString"

    def learnScala: String = learns("Scala")
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // Infix notation (operator notation)
  // works only with methods with a single parameter

  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary hangOutWith tom)
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))
  // All operators are methods.

  // Prefix notation
  // Unary operators
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // Postfix notation
  // Only available to methods without parameters
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  // 1. Overload the "+" operator which receive a string
  // mary + "the rockstar" => new person "Mary the rockstar"

  val maryTheRockStar = mary + "the rockstar"
  println(maryTheRockStar.name)

  // 2. add an age to the person class
  // add a unary + operator => new person with the age +1
  // +mary => mary with the age + 1
  println(mary.age)
  println((+mary).age)

  // 3. add a "learns" method in the person class => "mary learns Scala"
  // Add a learnScala method calls learns method witrh "Scala"
  // use it in postfix notation
  println(mary.learns("Rust"))
  println(mary learnScala)

  // 4. Overload the apply method
  // mary.apply(2) => "Mary watched Inception 2 times"
  println(mary(2))
}
