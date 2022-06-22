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

import exercises.Empty
// import playground.Cinderella
// import playground.PrinceCharming
// import playground._ // import everything
import playground.{PrinceCharming, Cinderella => Princess} // import aliasing

object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "Rock the JVM", 2018)

  // outside the package, we need to import the package

  // using an imported package
  val myEmptyList = Empty
  // using the fully qualified name
  val myList = new exercises.Cons(Empty, 1)

  // packages are in hierarchy matching the folder structure

  /*
  Package object
    each package can only contain 1 package object
    in a file named package.scala
    Allow for functions and values being defined outside of an object/class/trait

    Elements of the package object are visible troughout the packags
    without having to use the fully qualified name
   */
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val cinderella = new Princess
  val princeCharming = new PrinceCharming

  /* Default imports
        java.lang - String, Object, Exception
        scala - Int, Nothing, Function
        scala.Predef - println, ???
   */

}
