package memory

import spinal.core.{BitCount, BitVector, Bundle, UInt, in, out}
import spinal.lib.{Flow, master, slave}

class RamPort(dataType: BitVector, length: BitCount) extends Bundle with IInputOutput {
  val address = UInt(length)
  val rd = Flow(dataType)
  val wr = Flow(dataType)


  override def asInput() = {
    slave(wr)
    master(rd)
    in(address)
    this
  }

  override def asOutput() = {
    master(wr)
    slave(rd)
    out(address)
    this
  }

  def >>(that: RamPort): RamPort = {
    this.rd >> that.rd
    this.wr >> that.wr
    this.address >> that.address
    that
  }

  def <<(that: RamPort): RamPort = {
    this.rd << that.rd
    this.wr << that.wr
    this.address << that.address
    that
  }

  override type RefOwnerType = this.type
}
