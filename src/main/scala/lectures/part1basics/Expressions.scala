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

object Expressions extends App {
  val x = 1 + 2 // an expression
  println(x)

  println(2 + 3 * 4)
  // Math expressions
  // + - * / & | >> << >>>(right shift with 0-extension)

  println(1 == x)
  // relational operators
  // == != > >= < <=

  println(!(1 == x))
  // logical operators
  // ! && ||

  var aVariable = 2
  aVariable += 3
  println(aVariable)
  // += -= *= /=
  // only works with variables

  // Instructions (do smthg) vs Expressions (a value)(imperative programming)

  // IF expression
  val aCondition = true
  val aConditionedValue = if (aCondition) 5 else 3
  // if returns a values; thus is an expression
  println(aConditionedValue)

  // Loops
  var i = 0
  while (i < 10)
    println(i)
    i += 1
  // Never use while

  // In Scala, only use expressions

  val aWeirdValue = aVariable = 3
  // Unit in Scala is void
  println(aWeirdValue)
  // Unit can only be : `()`
  // In Scala, side effect == expression returning Unit
  // => loops are expressions returning Unit
  // => println returns Unit
  // reassignment returns Unit

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  // The value of a code block is the value of its last expression
  // Code blocks create scopes

  // 1. difference between "hello world" vs println("hello world") ?

  // "hello world" => an expression of type String
  // println("hello world") => an expression and an instruction causing side-effect (of type Unit)

  val someValue = {
    2 < 3
  }

  val someOtherValue = {
    if (someValue) 239 else 981
    41
  }
  // 2. What are the types of someValue and someOtherValue ?

  // someValue => Boolean
  // someOtherValue => Int
}
