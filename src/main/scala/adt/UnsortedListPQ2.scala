/* Alex M. Therwhanger, Derian M. Mowen, Asjal Ahmad */
package adt 

class UnsortedListPQ2[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private class Node(var data: A, var next: Node)
  private var head: Node = null
  private var tail: Node = null

  //This straight just add onto the end does not matter where node goes because it is unsorted.
  //O(1)
  def enqueue(a: A): Unit = {
    val x = new Node(a,null)
    tail = x
  
  
  }

  //O(n)
  //Traverse queue to compare priority
  //find highest priority and then change pointers
  /*
  * NOT COMPLETE
  * */
  def dequeue(): A = {
    val highestNode = findHighestQueue()
    // Problem removing in singly lnked list.
    highestNode.data
  }



  
  //O(1)
  def peek: A = findHighestQueue().data

  //O(1)
  def isEmpty:Boolean = head == null


  private def findHighestQueue():Node = {
    var d:Node = head
    var highest: Node = null
    while(d != null) {
      if(highest==null || higherPriority(d.data,highest.data)) highest = d
      d = d.next
    }
    return highest
  }
}


  

