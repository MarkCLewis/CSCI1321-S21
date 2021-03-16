package basics

import java.io.ObjectOutputStream
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.BufferedInputStream
import java.io.FileInputStream

case class Person(name: String, age: Int, accountBalance: Double)

object Serialization extends App {
  // val poorStudent = Person("Pat", 20, 1.54)
  // val csAlumnus = Person("Jane", 27, 1000000.56)

  // val oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("people.bin")))
  // oos.writeObject(poorStudent)
  // oos.writeObject(csAlumnus)
  // oos.close()

  val ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("people.bin")))
  val p1 = ois.readObject() match {
    case p: Person => p
    case _ => throw new RuntimeException("The file didn't have a person.")
  }
  val p2 = ois.readObject()
  ois.close()
  println(p1)
  println(p2)
}