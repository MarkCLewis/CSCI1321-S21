package adt

class DLLBuffer[A] extends MyBuffer[A] {
  private class Node(var data: A, var prev: Node, var next: Node)

  private val sentinel: Node = new Node(null.asInstanceOf[A], null, null)
  sentinel.prev = sentinel
  sentinel.next = sentinel

  private var numElems = 0

  def apply(index: Int): A = {
    require(index >= 0 && index < numElems)
    var rover = sentinel.next
    for (i <- 0 until index) rover = rover.next
    rover.data
  }

  def update(index: Int, a: A): Unit = {
    require(index >= 0 && index < numElems)
    var rover = sentinel.next
    for (i <- 0 until index) rover = rover.next
    rover.data = a
  }

  def insert(a: A,index: Int): Unit = {
    require(index >= 0 && index <= numElems)
    
    var rover = sentinel.next
    for (i <- 0 until index) rover = rover.next
    val n = new Node(a, rover.prev, rover)
    rover.prev.next = n
    rover.prev = n
    numElems += 1
  }

  def iterator = new Iterator[A] {
    private var rover = sentinel.next
    def hasNext: Boolean = rover != null
    def next(): A = {
      val ret = rover.data
      rover = rover.next
      ret
    }
  }

  def length: Int = numElems

  def remove(index: Int): A = {
    require(index >= 0 && index <= numElems)
    numElems -= 1
    var rover = sentinel.next
    for (i <- 0 until index) rover = rover.next
    val ret = rover.data
    rover.prev.next = rover.next
		rover.next.prev = rover.prev
    ret
  }
}
