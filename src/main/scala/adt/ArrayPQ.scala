package adt

class ArrayPQ[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  def enqueue(a: A): Unit = ???
  def dequeue(): A = ???
  def peek: A = ???
  def isEmpty:Boolean = ???
}