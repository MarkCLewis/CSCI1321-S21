package adt

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class TestQueue {
  var queue: ArrayQueue[Int] = null

  @Before def init = {
    queue = new ArrayQueue[Int]()
  }

  @Test def emptyOnCreate = {
    assertTrue(queue.isEmpty)
  }

  @Test def addRemove1 = {
    queue.enqueue(7)
    assertFalse(queue.isEmpty)
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemove3 = {
    queue.enqueue(7)
    queue.enqueue(5)
    queue.enqueue(3)
    assertFalse(queue.isEmpty)
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertEquals(3, queue.peek)
    assertEquals(3, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemove3Twice = {
    queue.enqueue(7)
    queue.enqueue(5)
    queue.enqueue(3)
    assertFalse(queue.isEmpty)
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertEquals(3, queue.peek)
    assertEquals(3, queue.dequeue())
    assertTrue(queue.isEmpty)
    queue.enqueue(8)
    queue.enqueue(6)
    queue.enqueue(4)
    assertFalse(queue.isEmpty)
    assertEquals(8, queue.peek)
    assertEquals(8, queue.dequeue())
    assertEquals(6, queue.peek)
    assertEquals(6, queue.dequeue())
    assertEquals(4, queue.peek)
    assertEquals(4, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveMany = {
    val nums = Array.fill(1000)(util.Random.nextInt())
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveManyTwice = {
    val nums = Array.fill(1000)(util.Random.nextInt())
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }
}