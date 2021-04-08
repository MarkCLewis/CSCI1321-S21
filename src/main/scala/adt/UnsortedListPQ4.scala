/* Shauna Odum, Janet Jiang, Donovan T. Givens */
package adt

class UnsortedListPQ4[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var default: A = _
  private class Node(var data: A, var prev: Node, var next: Node)
  private val end = new Node(default, null, null)
  end.next = end
  end.prev = end
  def enqueue(a: A): Unit = {
    val n = new Node(a, end, end.next)
    end.next.prev = n
    end.next = n
  }
  def dequeue(): A = {
    val n = findHigh()
    n.prev.next = n.next
    n.next.prev = n.prev
    n.data
  }
  def peek: A = findHigh().data

  private def findHigh(): Node = {
    var rover = end.next
    var highest = rover
    rover = rover.next
    while(rover != end){
      if (higherPriority(rover.data, highest.data)) highest = rover
      rover = rover.next
    }
    highest
  }
  
  def isEmpty:Boolean = end.next == end
  
  
}