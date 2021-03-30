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
    var rover = head
    for (i <- 0 until index-1) rover = rover.next
    val n = new Node(a, rover.next)
    rover.next = n
  }

  def iterator: Iterator[A] = ???

  def length: Int = numElems

  def remove(index: Int): A = ???
}