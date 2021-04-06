/* Shauna Odum, Janet Jiang, Donovan T. Givens */
package adt

class UnsortedListPQ4[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var default: A = _
  private class Node(var data: A, var prev: Node, var next: Node)
  private val end = new Node(default, null, null)
  end.next = end
  end.prev = end
  def enqueue(a: A): Unit = {
    //Same as in videos?
  }
  def dequeue(): A = {
    //Check priority as we dequeue
  }
  def peek: A = ???
  private def findHigh(a: Node): Node = {
    var a = end.next
    var b = a.next
    while(a != end){
      
    }  
  }
  
  def isEmpty:Boolean = end.next == end
  
  
}