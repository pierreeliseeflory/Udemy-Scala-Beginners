package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 => "three"
    case _ => "?" // wildcard
  }

  println(x)
  println(description)

  // a. cases are matched in order
  // b. if no match => MatchError
  // c. type of match expression: unification of all cases
  // d; pattern matching works well with case classes (default extractor patterns)

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bpb", 20)
  val greeting = bob match {
    case Person(n, a) if a < 21 =>
      s"Hi, my name is $n and I am $a years old and I can't drink in the US" // guard
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    // case _            => "hi"
  } // deconstruct a Person into constituant parts

  println(greeting)

  // 2. Pattern matching on sealed hierarchies
  sealed trait Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"Matched a dog of the $someBreed breed")
    case Parrot(x)      => println("parrot")
  }
  // sealed => the subclasses must be defined in the same file, thus the compiler knows the complete liste of classes and can issue warnings if the pattern matching is not exhaustive

  // ! matchin everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _               => false
  } // OVERKILL

  /*
   Exercise
    function based on pattern matching
    takes an Expr and turn it into human readable
    Sum(Number(2), Number(3)) => 2 + 3
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def print(e: Expr): String = {
    e match {
      case Number(x)   => s"$x"
      case Sum(e1, e2) => print(e1) + " + " + print(e2)
      case Prod(e1, e2) => {
        val x1 = e1 match {
          case Sum(e11, e12) => "(" + print(e1) + ")"
          case _             => print(e1)
        }
        val x2 = e2 match {
          case Sum(e21, e22) => "(" + print(e2) + ")"
          case _             => print(e2)
        }
        x1 + " * " + x2
      }
    }
  }

  println(print(Sum(Number(2), Number(3))))
  println(print(Prod(Sum(Number(5), Number(4)), Number(7))))
  println(print(Sum(Prod(Number(2), Number(1)), Number(3))))
  println(
    print(
      Prod(
        Sum(Number(2), Number(1)),
        Prod(Sum(Number(3), Number(4)), Number(7))
      )
    )
  )
}
