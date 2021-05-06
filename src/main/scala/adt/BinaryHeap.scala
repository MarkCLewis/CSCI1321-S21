package adt

import scala.reflect.ClassTag

class BinaryHeap[A : ClassTag](higherP: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var heap = Array.fill(10)(null.asInstanceOf[A])
  private var end = 1

  def enqueue(a: A): Unit = {
    var bubble = end
    while (bubble > 1 && higherP(a,heap(bubble/2))) {
      heap(bubble) = heap(bubble/2)
      bubble /= 2
    }
    heap(bubble) = a
    end += 1
  }

  def dequeue(): A = ???

  def peek: A = heap(1)

  def isEmpty:Boolean = end == 1
}