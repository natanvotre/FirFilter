package experiment

import spinal.core._
import spinal.core.Ram_2c_1w_1rs
import spinal.lib._

class FirFilter(Width: Int, Length: Int) extends Component{
  val io = new Bundle {
    val input  = master Stream SFix(Width exp, 0 bit)
    val output = slave  Stream SFix(Width exp, 0 bit)
  }

  io.input << io.output

  val mMem = new Memory(Width bits, Length bits, clockDomain)


  mMem.io.rd.addr   := 0
  mMem.io.rd.enable := False
  mMem.io.wr.addr   := 0
  mMem.io.wr.data   := 0
  mMem.io.wr.enable := True
}

//Define a custom SpinalHDL configuration with synchronous reset instead of the default asynchronous one. This configuration can be resued everywhere
object MySpinalConfig extends SpinalConfig(
  defaultConfigForClockDomains = ClockDomainConfig(resetKind = SYNC),
  targetDirectory = "rtl",
  device = Device.ALTERA
)

//Generate the MyTopLevel's Verilog using the above custom configuration.
object MyTopLevelVerilogWithCustomConfig {
  def main(args: Array[String]) {
    MySpinalConfig.generateVerilog(new FirFilter(Width = 16, Length = 16))
  }
}