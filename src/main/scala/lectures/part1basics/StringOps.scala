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

object StringOps extends App {
  val aString: String = "Hello, I am learning Scala"

  println(aString.charAt(2))
  println(aString.substring(7, 11))
  println(aString.split(" ").toList)
  println(aString.startsWith("Hello"))
  println(aString.replace(" ", "-"))
  println(aString.toLowerCase())
  println(aString.toUpperCase())
  println(aString.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt

  println('a' +: aNumberString :+ 'z')
  println(aString.reverse)
  println(aString.take(2))

  // Scala-specific: String interpolators

  // s-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  println(greeting)
  val anotherGreeting =
    s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(anotherGreeting)

  // f-interpolators
  // printf-like formatting
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minutes"
  // %2.2f -> 2 characters minimum and 2 decimals
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newline") // \n is not escaped
  val escaped = "This is a \n newline"
  println(raw"$escaped") // \n is escaped
}
