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

object OOBasics extends App {
  val person = new Person("John", 26)

  println(person)
  // println(person.name) // doesn't work
  println(person.age)
  println(person.x)

  person.greet("Daniel")

  val counter = new Counter

  println(counter.increment.getCount)
  counter.decrement

  counter.increment(5)
}

// constructor
// name is a Class parameter but not a field/member
// age is both a Class parameter and a Class field
class Person(name: String, val age: Int) {
  val x = 2 // a field

  println(1 + 3)

  // Method
  def greet(name: String): Unit = println(s"${this.name} says: Hi $name")
  // Use `this` to access class parameters

  // Overloading
  // Defining function with the same name but different arguments lists
  def gree(): Unit = println(s"Hi, I am $name") // here, this is implied

  // Overloading constructors
  // Mainly used for default arguments
  def this(name: String) = this(name, 0) // call the original constructor
  def this() = this("John Doe")

}

// 1. Novel and Wirter class
//  Writer: first name, surname, year
//    - method fullname
//  Novel: name, year of release, author
//    - method authorAge (age of the author at the time of writing)
//    - method isWrittenBy(author)
//    - method copy (new year of release) -> new instanceof Novel

class Writer(first_name: String, surname: String, val yearOfBirth: Int) {
  def fullname(): String = first_name + " " + surname
}

class Novel(title: String, yearOfWriting: Int, author: Writer) {
  def authorAge(): Int = yearOfWriting - author.yearOfBirth

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(year: Int): Novel = Novel(title, year, author)
}

// 2. Counter class
//  - receive an int value
//   - method getCount
//   - method increment (return new counter)
//   - overload inc/dec to receive an amount

class Counter(value: Int = 0) {
  def getCount: Int = value

  def increment = {
    println("incrementing")
    new Counter(value + 1) // immutability
  }
  def decrement = {
    println("decrementing")
    new Counter(value - 1)
  }

  def increment(value: Int): Counter = {
    if (value <= 0) this
    else
      increment.increment(
        value - 1
      ) // increment with parenthesis refers to the previous declaration (returning a new Counter)
  }
  def decrement(vlaue: Int): Counter = {
    if (value <= 0) this
    else
      decrement.decrement(value - 1)
  }
}
