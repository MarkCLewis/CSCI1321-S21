package adt

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class TestBSTMap {
  var bst: BSTMap[String, Int] = null

  @Before def createTree = bst = new BSTMap[String, Int](_ < _)

  @Test def emptyOnCreate = {
    assertTrue(bst.isEmpty)
  }

  @Test def addGetAFew = {
    assertTrue(bst.isEmpty)
    bst += "one" -> 1
    bst += "two" -> 2
    bst += "three" -> 3
    bst += "four" -> 4
    bst += "five" -> 5
    assertTrue(bst.nonEmpty)
    assertEquals(1, bst("one"))
    assertEquals(2, bst("two"))
    assertEquals(3, bst("three"))
    assertEquals(4, bst("four"))
    assertEquals(5, bst("five"))
  }

  @Test def addGetRemoveAFew = {
    assertTrue(bst.isEmpty)
    bst += "one" -> 1
    bst += "two" -> 2
    bst += "three" -> 3
    bst += "four" -> 4
    bst += "five" -> 5
    assertEquals(1, bst("one"))
    assertEquals(2, bst("two"))
    assertEquals(3, bst("three"))
    assertEquals(4, bst("four"))
    assertEquals(5, bst("five"))
    assertTrue(bst.nonEmpty)
    bst -= "one"
    assertEquals(None, bst.get("one"))
    assertEquals(2, bst("two"))
    assertEquals(3, bst("three"))
    assertEquals(4, bst("four"))
    assertEquals(5, bst("five"))
    bst -= "four"
    assertEquals(None, bst.get("one"))
    assertEquals(2, bst("two"))
    assertEquals(3, bst("three"))
    assertEquals(None, bst.get("four"))
    assertEquals(5, bst("five"))
    bst -= "five"
    assertEquals(None, bst.get("one"))
    assertEquals(2, bst("two"))
    assertEquals(3, bst("three"))
    assertEquals(None, bst.get("four"))
    assertEquals(None, bst.get("five"))
    assertTrue(bst.nonEmpty)
  }

  @Test def addGetRemoveMany = {
    var data = for (i <- 1 to 100) yield util.Random.nextString(5) -> util.Random.nextInt()
    for ((k, v) <- data) bst += k -> v
    assertTrue(bst.nonEmpty)
    for ((k, v) <- data) assertEquals(v, bst(k))
    for (_ <- 1 to 100) {
      val rnd = util.Random.nextInt(data.length)
      bst -= data(rnd)._1
      assertEquals(None, bst.get(data(rnd)._1))
      data = data.patch(rnd, Nil, 1)
      for ((k, v) <- data) assertEquals(v, bst(k))
    }
    assertTrue(bst.isEmpty)
  }
}