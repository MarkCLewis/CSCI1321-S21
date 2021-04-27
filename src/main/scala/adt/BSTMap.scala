package adt

import collection.mutable

class BSTMap[K, V](lt: (K, K) => Boolean) extends mutable.Map[K, V] {
  private class Node(val key: K, var value: V, var left: Node, var right: Node)
  private var root: Node = null

  def -=(key: K) = ???

  def +=(kv: (K, V)) = {
    def helper(n: Node): Node = {
      if (n == null) new Node(kv._1, kv._2, null, null)
      else {
        if (n.key == kv._1) {
          n.value = kv._2
        } else if (lt(kv._1, n.key)) {
          n.left = helper(n.left)
        } else {
          n.right = helper(n.right)
        }
        n
      }
    }
    root = helper(root)
    this
  }
  
  // Members declared in scala.collection.MapLike
  def get(key: K): Option[V] = {
    var rover = root
    while (rover != null && rover.key != key) {
      if (lt(key, rover.key)) rover = rover.left
      else rover = rover.right
    }
    if (rover == null) None else Some(rover.value)
  }

  def iterator = new Iterator[(K, V)] {
    val stack = collection.mutable.Stack[Node]()
    def pushAllLeft(n: Node): Unit = {
      if (n != null) {
        stack.push(n)
        pushAllLeft(n.left)
      }
    }
    def hasNext: Boolean = ???
    def next(): (K, V) = ???
  }

  def preorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = if (n!= null) {
      visit(n.key, n.value)
      helper(n.left)
      helper(n.right)
    }
    helper(root)
  }

  def postorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = if (n!= null) {
      helper(n.left)
      helper(n.right)
      visit(n.key, n.value)
    }
    helper(root)
  }

  def inorder(visit: (K, V) => Unit): Unit = {
    def helper(n: Node): Unit = if (n!= null) {
      helper(n.left)
      visit(n.key, n.value)
      helper(n.right)
    }
    helper(root)
  }
}