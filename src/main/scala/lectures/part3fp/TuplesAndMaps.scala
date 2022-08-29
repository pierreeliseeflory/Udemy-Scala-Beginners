package lectures.part3fp

object TuplesAndMaps extends App {
  // tuples = finite ordered "lists"
  val aTuple =
    new Tuple2(2, "hello, Scala") // Tuple2[Int, String] == (Int, String)
  val anotherTuple = (2, "hello, Scala")

  println(aTuple._1)
  println(aTuple._2)
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map().withDefaultValue(-1)

  val phoneBook = Map(("Jim", 555), "Daniel" -> 789) // syntactic sugar
  println(phoneBook)

  // map ops
  println(phoneBook.contains("Jim"))
  println(phoneBook("Jim"))
//   println(phoneBook("mary")) // crash
// to avoid crashs use .withDefaultValue(...)

  // add a pairing
  val newPairing = "Mary" -> 456
  val newPhoneBook = phoneBook + newPairing
  println(newPhoneBook)

  // functionals on maps
  // map, flatMap, filter

  println(phoneBook.map(pair => pair._1.toLowerCase() -> pair._2))
  // filterKeys
  println(phoneBook.filterKeys(x => x.startsWith("J")).toMap)
  // mapValues
  println(phoneBook.mapValues(number => "0245-" + number).toMap)

  // converions to other collections
  println(phoneBook.toList)
  println(List(("daniel", 555)).toMap)
  val names = List("Bob", "james", "mary", "daniel", "jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. what would happen if there are 2 original entries in a map "Jim" -> 555 and "JIM" -> 900 and we run : phoneBook.map(pair => pair._1.toLowerCase() -> pair._2) ?
    2. Overly simplified social network based on maps
      - Person = string (mapping to friends)
      - add a person to the network
      - remove a person
      - add friend (mutual)
      - unfriend

      stats
      - number of friends per person
      - person with most friends
      - people with no friends
      - is there a social connection between 2 peoples
   */

  val testMap: Map[String, Int] = Map(("Jim", 555), ("JIM", 900))
  println(testMap)
  println(testMap.map(pairing => (pairing._1.toLowerCase(), pairing._2)))
  // -> the pairings are overwritten: only the last pairring corresponding to the key remaain

  val socialNetwork: Map[String, List[String]] = Map()
  // we could change the List into a Set to avoid duplicates
  def addPerson(
      db: Map[String, List[String]],
      person: String
  ): Map[String, List[String]] = {
    db + (person -> List())
  }

  def addFriend(
      db: Map[String, List[String]],
      friend1: String,
      friend2: String
  ): Map[String, List[String]] = {
    // db.map(pair =>
    //   if pair._1 == friend1 then (pair._1, pair._2 :+ friend2)
    //   else if pair._1 == friend2 then (pair._1, pair._2 :+ friend1)
    //   else pair._1 -> pair._2
    // )
    // without copying each time
    db + (friend1 -> (db(friend1) :+ friend2)) + (friend2 -> (db(
      friend2
    ) :+ friend1))
  }

  def unfriend(
      db: Map[String, List[String]],
      friend1: String,
      friend2: String
  ): Map[String, List[String]] = {
    db.map(pair =>
      if pair._1 == friend1 then (pair._1, pair._2.filter(x => x != friend2))
      else if pair._1 == friend2 then
        (pair._1, pair._2.filter(x => x != friend1))
      else pair._1 -> pair._2
    )
  }

  def removePerson(
      db: Map[String, List[String]],
      person: String
  ): Map[String, List[String]] = {
    def unfriendList(
        db: Map[String, List[String]],
        person: String,
        friends: List[String]
    ): Map[String, List[String]] = {
      if (friends.isEmpty) db
      else
        unfriendList(unfriend(db, person, friends.head), person, friends.tail)
    }
    unfriendList(db, person, db(person))
      .filterKeys(name => name != person)
      .toMap
  }

  println(socialNetwork)
  val socialNetwork1: Map[String, List[String]] =
    addPerson(socialNetwork, "Tom")
  println(socialNetwork1)
  val socialNetwork2: Map[String, List[String]] =
    addPerson(socialNetwork1, "Tim")
  println(socialNetwork2)
  val socialNetwork3: Map[String, List[String]] =
    addPerson(socialNetwork2, "Tam")
  println(socialNetwork3)

  val socialNetwork4: Map[String, List[String]] =
    addFriend(socialNetwork3, "Tim", "Tam")
  println(socialNetwork4)
  val socialNetwork5: Map[String, List[String]] =
    addFriend(socialNetwork4, "Tim", "Tom")
  println(socialNetwork5)

  val socialNetwork6: Map[String, List[String]] =
    unfriend(socialNetwork5, "Tim", "Tom")
  println(socialNetwork6)

  val socialNetwork7: Map[String, List[String]] =
    removePerson(socialNetwork5, "Tom")
  println(socialNetwork7)

  def getFriendCount(db: Map[String, List[String]], person: String): Int = {
    db(person).size
  }
  println(getFriendCount(socialNetwork6, "Tim"))
  println(getFriendCount(socialNetwork6, "Tom"))

  def getMaxFriends(db: Map[String, List[String]]): String = {
    db.maxBy(pair => pair._2.size)._1
  }

  def getNoFriends(db: Map[String, List[String]]): Array[String] = {
    var losers: Array[String] = Array()
    for {
      profile <- db
    } yield {
      if profile._2.size == 0 then losers = losers :+ profile._1
    }
    losers
    // db.filterKeys(person => db(person).isEmpty)
  }

  getNoFriends(socialNetwork6).foreach(println)

  def findLink(
      db: Map[String, List[String]],
      start: String,
      target: String,
      history: List[String] = List()
  ): Boolean = {
    if db(start) contains target then true
    else
      val recurse = for (
        friends <- db(start)
        if !(history contains friends)
      )
        yield findLink(
          db,
          friends,
          target,
          history :+ start
        )
      recurse contains true
  }
  println(findLink(socialNetwork6, "Tom", "Tam"))
  println(findLink(socialNetwork6, "Tim", "Tam"))
  println(findLink(socialNetwork5, "Tom", "Tam"))
}
