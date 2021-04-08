/* Dillon D. Smith, Kohta Shimbara, Laura L. Fitzsimon, Logan D. Martinez */
package adt

import scala.reflect.ClassTag

/*
 * NOT FIXED FOR CLASS!!!
 */
class SortedArrayPQ1[A : ClassTag](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  var top = 0
  var end = 0
  var arr = Array.fill(10)(null.asInstanceOf[A])

  def enqueue(a: A): Unit = {
    var rover = arr.length - 1
    if(end >= arr.length){
      val tmp = Array.fill(arr.length * 2)(null.asInstanceOf[A])
      for (i <- 0 until arr.length){
        tmp(i) = arr(i)
      }
      arr = tmp
    }
    while(higherPriority(a, arr(rover))) {
      arr(rover + 1) = arr(rover)
      rover -= 1
    }
    arr(rover) = a
  }
  def dequeue(): A = {
    val ret = arr(end-1)
    end = end - 1
    ret
  }
  def peek: A = arr(top)
  def isEmpty:Boolean = top == end
}