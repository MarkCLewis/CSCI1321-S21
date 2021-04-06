/* Alex M. Therwhanger, Derian M. Mowen, Asjal Ahmad */
package adtar 

class UnsortedListPQ2[A](higherPriority: (A, A) => Boolean) extends MyPriorityQueue[A] {
  private class Node(var data: A, var next: Node)
  private var head: Node = null
  private var tail: Node = null

  //This straight just add onto the end does not matter where node goes because it is unsorted.
  //O(1)
  def enqueue(a: A): Unit = {
    val x = new Node(a,null)
    tail = x
    size++
  
  
  }

  //O(n)
  //Traverse queue to compare priority
  //find highest priority and then change pointers
  /*
  * NOT COMPLETE
  * */
  def dequeue(): A = {
    val highestNode = findHighestQueue()
    
    if(head==null)  null
    else if (size == 1) head.data
    else {
      retNode.data
      size--
    }
  }



  
  //O(1)
  def peek: A = findHighestQueue.data()

  //O(1)
  def isEmpty:Boolean = end.next == end


  private def findHighestQueue():Node = {
    val d:Node = null
    for(i <- front until end){
      d = higherPriority(i,i.next)
    }
    return d
  }
}


  

