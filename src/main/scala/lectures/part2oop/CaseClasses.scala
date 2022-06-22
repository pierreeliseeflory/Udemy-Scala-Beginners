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

object CaseClasses extends App {
  /*
        We usually have to reimplement many basic features
        equals, hashCode, toString, ...

        Case Classes define a class and its companion in one go
        along with basic features
   */
  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.age)

  // 2.sensible toString
  println(jim.toString)
  println(jim) // automatically delegate to toString method

  // 3. equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. handy copy method
  val jim3 = jim.copy
  val oldJim = jim.copy(age = 45)
  println(oldJim)

  // 5. Default case companion object
  val thePerson = Person
  println(thePerson)
  val mary = Person("Mary", 23)

  // 6. Serializable

  // 7. Extractor patterns: enable pattern matching

  // There are also case object but they don't have companions
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
