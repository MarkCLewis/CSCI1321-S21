package adt

import collection.mutable

class BSTMap[K, V] extends mutable.Map[K, V] {
  def -=(key: K) = ???
  def +=(kv: (K, V)) = ???
  
  // Members declared in scala.collection.MapLike
  def get(key: K): Option[V] = ???
  def iterator: Iterator[(K, V)] = ???
}