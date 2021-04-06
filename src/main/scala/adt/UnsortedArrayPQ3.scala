/* Lisel M. Faust, Konstantinos E. Partalas, Christopher M. Dailey */
package adt

class UnsortedArrayPQ3[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var back = 0
  private var data = Array.fill(10)(null.asInstanceOf[A])
  
  def enqueue(a: A): Unit = {
    // somehow replace calls to front with higher priority boolean checks
    // if ((back + 1) % data.length == front) {
    //   val tmp = Array.fill(data.length * 2)(null.asInstanceOf[A])
    //   for (i <- 0 until data.length-1) tmp(i) = data((front + i) % data.length)
    //   front = 0
    //   back = data.length - 1
    //   data = tmp
    // }
    // data(back) = a
    // back = (back + 1) % data.length
  }
  
  
  def dequeue(): A = {
    val ret = data(front)
    front = (front + 1) % data.length
    ret
  }
  def peek: A = {
    data(back)
  }
  def isEmpty:Boolean = back == 0
}