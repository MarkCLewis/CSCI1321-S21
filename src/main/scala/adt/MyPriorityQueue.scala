package adt

trait MyPriorityQueue[A] {
  def enqueue(a: A): Unit

  /**
    * Removes the element that has the highest priority.
    *
    * @return value with the highest priority.
    */
  def dequeue(): A
  def peek: A
  def isEmpty:Boolean
}