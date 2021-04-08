/* Lisel M. Faust, Konstantinos E. Partalas, Christopher M. Dailey */
package adt

import scala.reflect.ClassTag

class UnsortedArrayPQ3[A : ClassTag](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var back = 0
  private var data = Array.fill(10)(null.asInstanceOf[A])
  
  def enqueue(a: A): Unit = {
    if (back >= data.length) {
      val tmp = Array.fill(data.length * 2)(null.asInstanceOf[A])
      for (i <- 0 until data.length) tmp(i) = data(i)
      data = tmp
    }
    data(back) = a
    back += 1
  }
  
  
  def dequeue(): A = {
    val i = findHighestPriority()
    val ret = data(i)
    back -= 1
    data(i) = data(back)
    ret
  }
  def peek: A = {
    data(findHighestPriority())
  }
  def isEmpty:Boolean = back == 0

  def findHighestPriority(): Int = {
    var ret = 0
    for (i <- 1 until back) if (higherPriority(data(i), data(ret))) ret = i
    ret
  }
}