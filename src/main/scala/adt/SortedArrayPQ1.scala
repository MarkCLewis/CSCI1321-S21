/* Dillon D. Smith, Kohta Shimbara, Laura L. Fitzsimon, Logan D. Martinez */
package adt

class SortedArrayPQ1[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  var top = 0
  var end = 0
  var arr = Array.fill(10)(null)

  def enqueue(a: A): Unit = {
    var rover = arr.length - 1
    if(end != arr.length){
      while(higherPriority(a, arr(rover))) {
        arr(rover + 1) = arr(rover)
        rover -= 1
      }
      arr(rover) = a
    }else {
      var curarr = Array.fill(arr.length)(null)
      var i = 0
      while(i < curarr.length){
        curarr(i) = arr(i)
        i += 1
      }
      i = 0
      arr = Array.fill(arr.length * 2)(null)
      while(i < curarr.length){
        arr(i) = curarr(i)
        i += 1
      }
      while(higherPriority(a, arr(rover))) {
        arr(rover + 1) = arr(rover)
        rover -= 1
      }
      arr(rover) = a
    }
    

  }
  def dequeue(): A = {
    val ret = arr(top)
    if((top + 1) % arr.length == end) {
      //to do array is full
    }
    top = (top + 1) % arr length
    ret
  }
  def peek: A = arr(top)
  def isEmpty:Boolean = top == end
}