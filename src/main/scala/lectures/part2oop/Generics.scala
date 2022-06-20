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

object Generics extends App {
  class MyList[+A] { // generic type A
    // def add(element: A): MyList[A] = ??? // incorrect, A  could be a second subclass, which has an undefined result
    def add[B >: A](element: B): MyList[B] =
      ??? // Now we're sure that there isn't any confusion
    /*
        A = Cat
        B = Animal
        => turn a MyList[Cat] into a MyList[Animal]
     */
  }
  val listOfIntergers = new MyList[Int]
  val listOfStrings = new MyList[String]

  trait MyMap[Key, Value] // 2 generic types

  // generic methods
  object MyList { // objects cannot be type parameterized
    // the companion object

    def empty[A]: MyList[A] = ???
  }

  // the object since we didn't use "new"
  val emptyListOfIntergers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. Covariance : List[Cat] extends List[Animal]
  class CovariantList[+A]
  val animal: Animal = new Cat // polymorphic
  val covariantAnimalList: CovariantList[Animal] = new CovariantList[Cat]
  // covariantAnimalList.add(new Dog) ??? => MyList[Animal]

  // 2. Invariance : A generic class cannot be substituted by a subclass
  class InvariantList[A]
//   val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat] // incorrect

  // 3. Contravariance : A generic class can be substituted by a superclass
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]
  // used in some specific use cases
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types (<: or >:)
  class Cage[
      A <: Animal
  ](animal: A) // class Cage only accept types that are subclasses of Animal
  val cage = new Cage(new Dog)
  class Car
  // val cage = new Cage(new Car) // incorrect because `Car <: Animal` is false

  // expand MyList to be generic
}
