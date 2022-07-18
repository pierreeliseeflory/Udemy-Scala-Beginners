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

// trait MyPredicate[-T] { // A => Boolean
//   def test(element: T): Boolean
// }

// trait MyTransformer[-A, B] { // A => B
//   def transform(element: A): B
// }

// class EvenPredicate extends MyPredicate[Int] {
//   override def test(element: Int) = (element % 2) == 0
// }

// val EvenPredicate = new Function1[Int, Boolean] {
//   override def apply(element: Int) = (element % 2) == 0
// }

val EvenPredicate: Int => Boolean = _ % 2 == 0

// class StringToIntTransformer extends MyTransformer[String, Int] {
//   override def transform(element: String) = element.toInt
// }

// val StringToIntTransformer = new Function1[String, Int] {
//   override def apply(element: String) = element.toInt
// }

val StringToIntTransformer: String => Int = _.toInt

// class ConsecutiveTransformer extends MyTransformer[Int, MyList[Int]] {
//   override def transform(element: Int) = Empty.add(element).add(element + 1)
// }

// val ConsecutiveTransformer = new Function1[Int, MyList[Int]] {
//   override def apply(element: Int) = Empty.add(element).add(element + 1)
// }

val ConsecutiveTransformer: Int => MyList[Int] = (x: Int) =>
  Empty.add(x).add(x + 1)

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

  // higher order functions
  // either receive or return functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def concat[B >: A](additionalList: MyList[B]): MyList[B]
  def ||[B >: A](additionalList: MyList[B]): MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(ordering: (A, A) => Int): MyList[A]
  def zipWith[B, C](other: MyList[B], zipper: (A, B) => C): MyList[C]
  def fold[B >: A](start: B)(op: (A, B) => B): B
}

case object Empty extends MyList[Nothing] {
  def head = throw new NoSuchElementException
  def tail = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): Cons[B] = new Cons(Empty, element)
  def printElements: String = ""

  def map[B](transformer: Nothing => B) = Empty
  def filter(predicate: Nothing => Boolean) = Empty
  def concat[B >: Nothing](
      additionalList: MyList[B]
  ): MyList[B] = additionalList
  def ||[B >: Nothing](additionalList: MyList[B]): MyList[B] =
    concat(additionalList)
  def flatMap[B](transformer: Nothing => MyList[B]) =
    Empty

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(ordering: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](other: MyList[B], zipper: (Nothing, B) => C) =
    if (!other.isEmpty)
      throw new RuntimeException("Lists do not have the same size")
    Empty
  def fold[B >: Nothing](start: B)(op: (Nothing, B) => B): B = start
}

case class Cons[A](t: MyList[A], h: A) extends MyList[A] {
  def head = h
  def tail = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(this, element)
  def printElements: String =
    if (t.isEmpty) s"$h"
    else s"${t.printElements} $h"

  def map[B](transformer: A => B): MyList[B] =
    t.map(transformer).add(transformer(h))
  def filter(predicate: A => Boolean): MyList[A] = if (predicate(head))
    tail.filter(predicate).add(head)
  else tail.filter(predicate)

  def concat[B >: A](additionalList: MyList[B]): MyList[B] =
    if (additionalList.isEmpty) this
    else this.concat(additionalList.tail).add(additionalList.head)
  def ||[B >: A](additionalList: MyList[B]): MyList[B] =
    concat(additionalList)
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    t.flatMap(transformer).concat(transformer(h))

  // hofs
  def foreach(f: A => Unit): Unit =
    f(h)
    tail.foreach(f)
  def sort(ordering: (A, A) => Int): MyList[A] = {
    def insert(
        x: A,
        sortedList: MyList[A]
    ): MyList[A] =
      if (sortedList.isEmpty) new Cons(Empty, x)
      else if (ordering(x, sortedList.head) < 0)
        new Cons(insert(x, sortedList.tail), sortedList.head)
      else new Cons(sortedList, x)

    val sortedTail = tail.sort(ordering)
    insert(h, sortedTail)
  }
  def zipWith[B, C](other: MyList[B], zipper: (A, B) => C): MyList[C] =
    if (other.isEmpty)
      throw new RuntimeException("Lists do not have the same size")
    new Cons(tail.zipWith(other.tail, zipper), zipper(head, other.head))
  def fold[B >: A](start: B)(op: (A, B) => B): B =
    tail.fold(op(head, start))(op)
}

object ListTest extends App {
  val list = new Cons(new Cons(new Cons(Empty, 1), 2), 3)

  println(list.add(17).head)

  println(list.toString)

  val list2 = Empty.add("1").add("2").add("3").add("4")
  println(list2.toString)

  println(list2.map(elem => elem.toInt))
  /*
  With an anonymous class
    println(list2.map[Int](new MyTransformer[Int, String]) {
      override def test(element: Int) = (element % 2) == 0
    })
   */
  println(list2.map(elem => elem.toInt).filter(elem => elem % 2 == 0))

  println(list.flatMap(x => Empty.add(x).add(x + 1)))

  println((list || list2.map(elem => elem.toInt)).toString)

  val cloneOfList = new Cons(new Cons(new Cons(Empty, 1), 2), 3)
  // enabled by the case class
  println(list == cloneOfList)

  val unsortedList = new Cons(
    new Cons(new Cons(new Cons(new Cons(new Cons(Empty, 5), 20), 3), 1), 2),
    -1
  )
  unsortedList.foreach(x => println(x))
  println(unsortedList.sort((x: Int, y: Int) => x - y))
  println(list.zipWith(Empty.add(3).add(2).add(1), (x, y) => 2 * x + y))
  println(list.fold(4)((x, y) => x - y))
  println(list.fold(4)(_ + _))

  val testForComprehensions = for {
    x <- list
    y <- list2
  } yield x + y
  println(testForComprehensions)
}
