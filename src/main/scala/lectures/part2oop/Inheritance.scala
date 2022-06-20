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

object Inheritance extends App {
  class Animal { // the superclass of Cat
    val creaturetype = "Wild"

    def eat = println("nomnom")
    private def sleep = println("zzz") // only accessible from the class
    protected def dring = println(
      "gloups"
    ) // only accessible from the class and subclasses (not instances)
  }

  class Cat extends Animal { // a subclass of Animal
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  // cat.eat // unaccessible outside class and sublclass
  cat.crunch

  // Scala uses single class inheritance
  // meaning you can only extend 1 class at a time

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String)
      extends Person(
        name
      ) // the JVM requires that you call a super constructor first

  // overriding
  class Dog extends Animal {
    override def eat = println("crunch, crunch")
    override val creaturetype: String = "domestic"
  }

  class Canine(override val creaturetype: String) extends Animal { // fields can be overriden in the constructor
    override def eat = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog
  dog.eat
  println(dog.creaturetype)
  val canine = new Canine("K9")
  println(canine.creaturetype)

  // Type substitution == Polymorphism
  val unknownAnimal: Animal = new Canine("K9")
  unknownAnimal.eat // use the higer level of overriding

  // super
  // call the parents implementation of a method or field

  // preventing overrides
  // 1 - use final on the member
  // 2 - use final on the entire class (prevent the extension of a class (base types are final in Scala))
  // 3 - use sealed on the class (allow to extend the class in this file but prevent extensions in other files)
}
