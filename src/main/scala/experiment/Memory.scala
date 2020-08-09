package experiment

import spinal.core._
import spinal.core.{BitCount, Bool, Bundle}
import spinal.lib.{IMasterSlave, MS, MSFactory, Stream, StreamFactory, StreamFragmentFactory, master}

class Memory(Width: BitCount, Length: BitCount, clock: ClockDomain) extends BlackBox {
  val io = new Bundle {
    val clk = in Bool
    val rd  = read  MStream(Width, Length)
    val wr  = write MStream(Width, Length)
  }

  mapClockDomain(clock,io.clk)
  noIoPrefix()
}

class MStream(Width: BitCount, Length: BitCount) extends Bundle with IReadWrite {
  val addr = UInt(Length)
  val data = UInt(Width)
  val enable = Bool

  override def asWrite(): Unit = {
    in(addr, enable, data)
  }

  override def asRead(): Unit = {
    in(addr, enable)
    out(data)
  }

  override type RefOwnerType = this.type
}

trait IReadWrite {

  var isWriteInterface: Boolean = false

  def asRead(): Unit
  def asWrite(): Unit
}

object read extends RW {
  override def applyIt[T <: IReadWrite](i: T) = {
    i.asRead()
    i.isWriteInterface = false
    i
  }
}

object write extends RW {
  override def applyIt[T <: IReadWrite](i: T) = {
    i.asWrite()
    i.isWriteInterface = true
    i
  }
}

trait RW {
  def applyIt[T <: IReadWrite](i: T) : T
  def MStream(w:BitCount,l:BitCount) = applyIt(new MStream(w, l))
}
