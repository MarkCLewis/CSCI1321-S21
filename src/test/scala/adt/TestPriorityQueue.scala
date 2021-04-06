package adt

import org.junit.Test
import org.junit.Before
import org.junit.Assert._

class TestPriorityQueue {
  var queue: MyPriorityQueue[Int] = null

  @Before def init = {
    queue = new SortedArrayPQ0[Int](_ < _)
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
    assertEquals(3, queue.peek)
    assertEquals(3, queue.dequeue())
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemove3Twice = {
    queue.enqueue(7)
    queue.enqueue(5)
    queue.enqueue(3)
    assertFalse(queue.isEmpty)
    assertEquals(3, queue.peek)
    assertEquals(3, queue.dequeue())
    assertEquals(5, queue.peek)
    assertEquals(5, queue.dequeue())
    assertEquals(7, queue.peek)
    assertEquals(7, queue.dequeue())
    assertTrue(queue.isEmpty)
    queue.enqueue(4)
    queue.enqueue(8)
    queue.enqueue(6)
    assertFalse(queue.isEmpty)
    assertEquals(4, queue.peek)
    assertEquals(4, queue.dequeue())
    assertEquals(6, queue.peek)
    assertEquals(6, queue.dequeue())
    assertEquals(8, queue.peek)
    assertEquals(8, queue.dequeue())
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveMany = {
    val nums = Array.fill(1000)(util.Random.nextInt())
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums.sorted) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }

  @Test def addRemoveManyTwice = {
    val nums = Array.fill(1000)(util.Random.nextInt())
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums.sorted) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
    for (n <- nums) queue.enqueue(n)
    assertFalse(queue.isEmpty)
    for (n <- nums.sorted) {
      assertEquals(n, queue.peek)
      assertEquals(n, queue.dequeue())
    }
    assertTrue(queue.isEmpty)
  }
}