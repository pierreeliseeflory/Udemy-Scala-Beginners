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

object DefaultArgs extends App {
  def tailrecursiveFactorial(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc
    else tailrecursiveFactorial(n - 1, n * acc)

  val fact10 = tailrecursiveFactorial(10)
  println(fact10)

  def savePicture(
      format: String = "jpg",
      width: Int = 1920,
      height: Int = 1080
  ): Unit =
    println(
      "blabla"
    )
//   savePicture(800, 600) // incorrect because passed arguments corresponds to the first args
// we can also name the arguments
  savePicture(width = 800)
}
