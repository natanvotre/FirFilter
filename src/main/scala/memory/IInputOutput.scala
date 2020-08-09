package memory

import spinal.core.{BitCount, BitVector, IODirection}

trait IInputOutput {
  var isInputInterface: Boolean = false

  def asInput(): IInputOutput
  def asOutput(): IInputOutput
}

trait IIO {
  def applyIt[T <: IInputOutput](i: T) : T
  def RamPort[T <: BitVector](vector: T, l:BitCount) = applyIt(new RamPort(vector, l))
}

object input extends IIO {
  override def applyIt[T <: IInputOutput](i: T) = {
    i.asInput()
    i.isInputInterface = true
    i
  }
}

object output extends IIO {
  override def applyIt[T <: IInputOutput](i: T) = {
    i.asOutput()
    i.isInputInterface = false
    i
  }
}
