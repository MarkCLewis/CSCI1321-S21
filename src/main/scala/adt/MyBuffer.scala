package adt

trait MyBuffer[A] {
  def remove(index: Int): A
  def insert(a: A, index: Int): Unit
  def apply(index: Int): A
  def update(index: Int, a: A): Unit
  def length: Int

  def iterator: Iterator[A]
}