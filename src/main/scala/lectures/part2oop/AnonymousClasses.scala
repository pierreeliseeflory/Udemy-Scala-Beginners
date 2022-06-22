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

object AnonymousClasses extends App {

  abstract class Animal { // or trait
    def eat: Unit
  }

  // anonymous class
  // pass a definition on the spot
  val funnyAnimal = new Animal {
    override def eat: Unit = println("ahahah")
  }
  /*
    equivalent with
    class AnonymousClasses$$anon$1 extends Animal {
        override def eat: Unit = println("ahahah")
    }
    val funnyAnimal = new AnonymousClasses$$anon$1
   */
  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help ?")
  }
  val jim =
    new Person("Jim") { // pass the required constructor arguments even anonymously
      override def sayHi: Unit = println(
        s"Hi, my name is Jim, how can I be of service ?"
      )
    }

  // Anonymous classes work for both anonymous and non-anonymous classes

  /*
  Exercices
    Expand MyList:
        1. Generic trait MyPredicate[-T]: check if a value of type T passes a conditions with a method test[T]: Boolean
        2. Generic trait MyTransformer[-A, B]: convert a value of type A to type B
        3. MyList:
            - map(transformer) => MyList
            - filter(predicate) => MyList
            - flatMap(transformer from A to Mylist[B]) => MyList[B]

        class EvenPredicate extends MyPredicate[Int] returns if it is a even
        class StringToIntTransformer extends Mytransformer[String, Int]

        [1, 2, 3].map(n * 2) = [2, 4, 6]
        [1, 2, 3, 4].filter(n % 2 == 0) = [2, 4]
        [1, 2, 3].flatMap(n => [n, n+1]) => [1, 2, 2, 3, 3, 4]
   */
}
