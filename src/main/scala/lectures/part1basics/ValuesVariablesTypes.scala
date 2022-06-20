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

object ValuesVariablesTypes extends App {
  // Values
  val x: Int = 42
  println(x)

  // x = 2 => incorrect
  // vals are immutable

  val y = 42 // types can be inferred by the compiler

  val z: Int = 3;
  // semi-colons are allowed but not mandatory
  // but expressions are usually written on their own line

  // Types
  val aBoolean: Boolean = true // or false
  val aChar: Char = 'a'
  val aString: String = "hello";
  val anInt: Int = x
  val aShort: Short = 12345
  val aLong: Long =
    123456789123456789L // add a 'L' to differentiate from an Int
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // Variables
  var aVariable: Int = 4
  aVariable = 5 // variables are mutable
}
