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

package exercises

abstract class MyList[+A] {
  /*
        head = first element of the list
        tail = remainder of the list
        isEmpty
        add(int) = new list with this element added
        toString = a stringh representation of the list
   */
  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  // following override is mandatory because toString is a class member of AnyRef
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): Cons[B] = new Cons(Empty, element)
  def printElements: String = ""
}

class Cons[A](t: MyList[A], h: A) extends MyList[A] {
  def head = h
  def tail = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(this, element)
  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"${t.printElements} $h"
}

object ListTest extends App {
  val list = new Cons(new Cons(new Cons(Empty, 1), 2), 3)

  println(list.add(17).head)

  println(list.toString)

  val list2 = Empty.add(1).add(2).add(3)
  println(list2.toString)
}
