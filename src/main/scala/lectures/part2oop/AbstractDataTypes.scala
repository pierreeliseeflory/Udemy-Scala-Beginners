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

object AbstractDataTypes extends App {
  // abstract classes cannot be instanciated
  // abstract can have both abstract and not abstract fields
  abstract class Animal {
    val creaturetype: String // abstract value
    def eat: Unit // abstract method
  }

  class Dog extends Animal { // must implement the abstract fields of the superclass
    override val creaturetype: String = "Canine"
    override def eat: Unit = println("crunch crunch")
  }

  // Traits
  // Traits can have abstract fields
  // Traits can be inherited in addition from a class
  // classes can inheritate from any number of Traits
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    val creaturetype: String = "croc" // override keyword not mandatory
    def eat: Unit = println("nomnom")
    def eat(animal: Animal): Unit =
      println(s"I'm a croc and i'm eating a ${animal.creaturetype}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract classes
  // 1 - traits don't have constructor parameters
  // 2 - multiple traits may be inherited by the same class
  // 3 - traits define behavior

  // Types in Scala
  //                    scala.Any (scala type top parent)
  //                                |
  // scala.anyVal (base types) -----|------   scala.anyRef
  //                                                \
  //                                     String, List, Set, ..., [user defined class]
  //                                                        \|/
  //                                            scala.Null (everything can be replaced by a Null)
  //            \                       |                       /
  //                                scala.Nothing
}
