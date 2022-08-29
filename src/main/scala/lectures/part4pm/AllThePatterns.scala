package lectures.part4pm

import exercises.MyList
import exercises.Empty
import exercises.Cons

object AllThePatterns extends App {
  // 1 - constants
  val x: Any = "Scala"
  val constants = x match {
    case 1              => " a number"
    case "Scala"        => "The Scala"
    case true           => "The truth"
    case AllThePatterns => "A singleton object"
  }

  // 2 - match anything
  // 2.1 - wildcard
  val matchAnything = x match {
    case _ =>
  }

  // 2.2 - A variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3 - tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1)         => ""
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => v
  }

  // 4 - case classes - constructor pattern
  val aList: MyList[Int] = Cons(Cons(Empty, 2), 1)
  val matchAList = aList match {
    case Empty                              =>
    case Cons(Cons(subhead, subtail), tail) =>
  }

  // 5 - list patterns
  val aStandardList = List(1, 2, 3, 4)
  val standardListMatching = aStandardList match {
    case List(1, _, _, _)   => // extractor
    case 1 :: List(_)       => // infix pattern
    case List(1, _*)        => // List of arbitrary length
    case List(1, 2, 3) :+ 4 => // infix pattern
    case _                  =>
  }

  // 6 - type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => // explicit type scifier
    case _               =>
  }

  // 7 - name binding
  val nameBindingMatch = aList match {
    case Cons(rest @ Cons(_, 2), 1) => // name binding inside nested patterns
    case nonEmptyList @ Cons(_, _)  => // name binding => use the name later
  }

  // 8 - multi-patterns
  val multipattern = aList match {
    case Empty | Cons(_, 0) => // compound pattern (multi-pattern)
    case _                  =>
  }

  // 9 - if guards
  val secondElementSpecial = aList match {
    case Cons(Cons(_, specialElement), _) if specialElement % 2 == 0 =>
  }

  val numbers = List(1, 2, 3, 4)
  val numberMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings"
    case listOfNumbers: List[Int]    => "a list of numbers"
    case _                           => ""
  }
  println(numberMatch)

}
