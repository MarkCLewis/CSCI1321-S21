package adt

trait MyQueue[A] {
  def enqueue(a: A): Unit

  /**
    * Removes the element that has been in the queue the longest and returns the value.
    *
    * @return value that has been in the queue the longest.
    */
  def dequeue(): A
  def peek: A
  def isEmpty:Boolean
}