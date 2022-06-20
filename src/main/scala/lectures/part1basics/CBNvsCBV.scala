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

object CBNvsCBV extends App {
  def callByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
    // has a fixed value equivalent to the value of the expression passsed as argument
  }

  def callByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
    // x is evaluated each time
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

//   printFirst(infinite(), 34)
  printFirst(34, infinite())
}
