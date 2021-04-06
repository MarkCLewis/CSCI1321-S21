/* Jessica E. Garcia-Tejeda, Gavin C. Wilson, Julian M. Santovena, Trey Lisauckis */

package adt

//smallest elements in the back
//dequeue the biggest element 
//biggest element in the front

class SortedArrayPQ0[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
private var front, back = 0
  var arr = new Array[A](10)
  
  def enqueue(a: A) = {
    var index:Int = 0;
    while (i in 0 until arr.length) {
      if (higherPriority(a, arr(i))) index = i;
    }
  }
  def enqueue(a: A): Unit = {
    if ((back + 1) % data.length == front) {
      val tmp = new Array[A](data.length * 2)
      for(i <- 0 until data.length - 1) tmp(i) = data((i + front) % data.length)
      front = 0
      back = data.length - 1
      data = tmp 
    }
    data(back) = obj
    back = (back + 1) % data.length
  }
  def dequeue(): A = {
    asert(!isEmpty, "Dequeue called on an empty Queue.")
    val ret = data(front)
    front = (front+1) % data.length
    ret
  }
  def peek: A = arr(front)
  def isEmpty:Boolean = front == back
}