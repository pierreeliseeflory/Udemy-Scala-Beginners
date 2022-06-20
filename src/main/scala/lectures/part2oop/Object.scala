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

object Object extends App {
  // Scala does not have class-level functionality (ie static)

  object Person {
    // static/class-level functionnality
    val N_EYES = 2
    def canFly: Boolean = false

    // a factory method
    // its only goal is to create instances
    def from(mother: Person, father: Person): Person = new Person("Bobbie")
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  // objects don't receive parameters
  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = Singleton instance
  val mary = Person // the instance
  val john = Person
  println(mary == john) // same instances

  class Person(val name: String) {
    // instance-level functionnality
  }

  // The class Person and the object Person are companions
  // each companion can access each other's private members
  // The Companions design pattern in Scala
  // everything is retrieved from an instance (a singleton or a regular one)
  // i.e Scala is reaaly Object-Oriented

  val mary2 = new Person("Mary") // an instance (i.e usual class instance)
  val john2 = new Person("John")
  println(mary2 == john2)

  val bobbie1 = Person.from(mary2, john2)
  val bobbie2 = Person.apply(mary2, john2)
  val bobbie3 = Person(mary2, john2)

  // Scala Applications
  // A Scala object with :
  // def main(args: Array[String]): Unit because it is turned into a Java Application
  // public static void main(args: Array[String]) in Java
  // if the object doesn't extend App, we need this main method

}
