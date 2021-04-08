/* Jessica E. Garcia-Tejeda, Gavin C. Wilson, Julian M. Santovena, Trey Lisauckis */

package adt

import scala.reflect.ClassTag

//smallest elements in the back
//dequeue the biggest element 
//biggest element in the front

class SortedArrayPQ0[A : ClassTag](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private var back = 0
  var data = new Array[A](10)
  
  def enqueue(a: A): Unit = {
    if (back + 1 >= data.length) {
      val tmp = new Array[A](data.length * 2)
      for(i <- 0 until data.length) tmp(i) = data(i)
      data = tmp 
    }
    var index:Int = back - 1;
    while (index >= 0 && higherPriority(data(index), a)) {
      data(index + 1) = data(index)
      index -= 1;
    }
    data(index + 1) = a
    back += 1
  }
  def dequeue(): A = {
    assert(!isEmpty, "Dequeue called on an empty Queue.")
    val ret = data(back-1)
    back -= 1
    ret
  }
  def peek: A = data(back-1)
  def isEmpty:Boolean = back == 0
}