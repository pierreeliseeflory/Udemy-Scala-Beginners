package lectures.part3fp

import java.util.Random

object Sequences extends App {
  // Sequences - Seq
  // order + index
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // == get
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  (1 to 10).foreach(x => println("hello"))

  // Lists
  val aList = List(1, 2, 3)
  val prepended = 42 :: aList
  val prepended2 = 42 +: aList
  val appended = aList :+ 42
  println(prepended)
  println(prepended2)
  println(appended)

  val apples5 = List.fill(5)("apple") // curried function
  println(apples5)
  println(aList.mkString("-"))

  // Arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println) // the array is initilaized with default values

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val newVal = r.nextInt()
      val modifiedIndex = r.nextInt(maxCapacity)
      val currentTime = System.nanoTime()
      collection.updated(modifiedIndex, newVal)
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // Lists (linked lists)
  // + modification of first element fast
  // - updating an element in the middle takes long
  println(getWriteTime(numbersList)) // 7000591.197
  // Vectors: 32-ary search tree
  // - modification of an element modifies a 32 element chunk
  // + small depth
  println(getWriteTime(numbersVector)) // 5871.289
}
