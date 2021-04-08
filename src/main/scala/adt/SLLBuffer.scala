package adt

class SLLBuffer[A] extends MyBuffer[A] {
  private class Node(var data: A, var next: Node)

  private var head: Node = null
  private var tail: Node = null
  private var numElems = 0

  def apply(index: Int): A = {
    require(index >= 0 && index < numElems)
    var rover = head
    for (i <- 0 until index) rover = rover.next
    rover.data
  }

  def update(index: Int, a: A): Unit = {
    require(index >= 0 && index < numElems)
    var rover = head
    for (i <- 0 until index) rover = rover.next
    rover.data = a
  }

  def insert(a: A,index: Int): Unit = {
    require(index >= 0 && index <= numElems)
    if (index == 0) {
      head = new Node(a, head)
      if (tail == null) tail = head
    } else {
      var rover = head
      for (i <- 0 until index-1) rover = rover.next
      val n = new Node(a, rover.next)
      rover.next = n
      if (n.next == null) tail = n
    }
    numElems += 1
  }

  def iterator = new Iterator[A] {
    private var rover = head
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
    if (index == 0) {
      val ret = head.data
      head = head.next
      if (head == null) tail = null
      ret
    } else {
      var rover = head
      for (i <- 0 until index-1) rover = rover.next
      val ret = rover.next.data
      rover.next = rover.next.next
      if (rover.next == null) tail = rover
      ret
    }
  }
}