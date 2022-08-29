package lectures.part4pm

import lectures.part2oop.AbstractDataTypes.Animal

object BracelessSyntax {
  // Scala 3 can remove all braces
  // => Significant indentation

  // if - expressions
  val anIfExpression = if (2 < 3) "bigger" else "smaller"

  // java-style
  val anIfExpression_v2 =
    if (2 < 3) {
      "bigger"
    } else {
      "smaller"
    }

  // compact
  val anIfExpression_v3 =
    if (2 < 3) "bigger"
    else "smaller"

  // scala 3 (then)
  val anIfExpression_v4 =
    if 2 < 3 then "bigger"
    else "smaller"

  val anIfExpression_v5 =
    if 2 < 3 then
      val result = "bigger"
      result
    else
      val result = "smaller"
      result

  // scala 3 one-liner
  val anIfExpression_v6 = if 2 < 3 then "bigger" else "smaller"

  // for-comprehension
  val forComprehension = for {
    n <- List(1, 2, 3)
    s <- List("black", "white")
  } yield s"$n$s"

  // scala 3
  val forComprehension_v2 =
    for
      n <- List(1, 2, 3)
      s <- List("black", "white")
    yield s"$n$s"

  // pattern-matching
  val meaningOfLife = 42
  val aPatternMatch = meaningOfLife match {
    case 1 => "1"
    case 2 => "2"
    case _ => "?"
  }

  // scala 3
  val aPatternMatch_v2 = meaningOfLife match
    case 1 => "1"
    case 2 => "2"
    case _ => "?"

  // method without braces
  def computeMeaningOfLife(arg: Int): Int = {
    val partialResult = 40

    partialResult + 2
  }

  def computeMeaningOfLife_v2(arg: Int): Int =
    val partialResult = 40

    partialResult + 2

  // class definition with significant indentation (same for traits, objects, enums, etc)
  class Animal: // use colon instead of brace
    def eat(): Unit =
      println("i'm eating")

    def grow(): Unit =
      println("i'm growing")
  // 1000s lines of code
  // end token
  end Animal

  // anonymous classes
  val aSpecialAnimal = new Animal:
    override def eat(): Unit = println("i'm special -")

  def main(args: Array[String]): Unit = {
    println(anIfExpression_v5)
    println(computeMeaningOfLife(2))
  }

}
