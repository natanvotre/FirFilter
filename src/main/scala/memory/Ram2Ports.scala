package memory

import spinal.core.{B, BitCount, Bits, ClockDomain, ClockDomainConfig, Component, Device, False, IntToBuilder, SYNC, SpinalConfig, True, when}



class Ram2Ports(memClockDomain: ClockDomain,
                device: Device,
                width: BitCount,
                length: BitCount
               ) extends Component {
  val portA = input RamPort(Bits(width), length)
  val portB = input RamPort(Bits(width), length)

  if (device == Device.ALTERA) {
    val altSyncRam = new AltSyncRam(
      addressRegB = "CLOCK1",
      clockEnableInputA = "BYPASS",
      clockEnableInputB = "BYPASS",
      clockEnableOutputA = "BYPASS",
      clockEnableOutputB = "BYPASS",
      inDataRegB = "CLOCK1",
      intendedDeviceFamily = "Cyclone V",
      lpmType = "altsyncram",
      numWordsA = 1 << length.value,
      numWordsB = 1 << length.value,
      operationMode = "BIDIR_DUAL_PORT",
      outDataAclrA = "NONE",
      outDataAclrB = "NONE",
      outDataRegA = "CLOCK0",
      outDataRegB = "CLOCK1",
      powerUpUninitialized = "FALSE",
      readDuringWriteModeMixedPorts = "DONT_CARE",
      readDuringWriteModePortA = "NEW_DATA_NO_NBE_READ",
      readDuringWriteModePortB = "NEW_DATA_NO_NBE_READ",
      widthAddressA = length,
      widthAddressB = length,
      widthA = width,
      widthB = width,
      widthByteEnableA = 1,
      widthByteEnableB = 1,
      wrControlWrAddressRegB = "CLOCK1",
      AClockDomain = memClockDomain,
      BClockDomain = memClockDomain
    )
    altSyncRam.io.address_a := portA.address
    altSyncRam.io.address_b := portB.address
    altSyncRam.io.data_a := B(portA.wr.payload)
    altSyncRam.io.data_b := B(portB.wr.payload)
    altSyncRam.io.wren_a := portA.wr.valid
    altSyncRam.io.wren_b := portB.wr.valid
    altSyncRam.io.aclr0 := False
    altSyncRam.io.aclr1 := False
    altSyncRam.io.addressstall_a := False
    altSyncRam.io.addressstall_b := False
    altSyncRam.io.byteena_a := True
    altSyncRam.io.byteena_b := True
    altSyncRam.io.clocken0 := True
    altSyncRam.io.clocken1 := True
    altSyncRam.io.clocken2 := True
    altSyncRam.io.clocken3 := True
    altSyncRam.io.eccstatus := 0
    altSyncRam.io.rden_a := True
    altSyncRam.io.rden_b := True

    portA.rd.valid := True
    portB.rd.valid := True
    portA.rd.payload := altSyncRam.io.q_a
    portB.rd.payload := altSyncRam.io.q_b

  } else {
    throw new Exception("Not implemented yet!")
  }
}

//Define a custom SpinalHDL configuration with synchronous reset instead of the default asynchronous one. This configuration can be reused everywhere
object AlteraConfig extends SpinalConfig (
  defaultConfigForClockDomains = ClockDomainConfig(resetKind = SYNC),
  targetDirectory = "rtl",
  device = Device.ALTERA
)

trait Ram2PortConfig {
  def generateRam2Port(spinalConfig: SpinalConfig, width: Int, length: Int): Unit = {
    println("spinalConfig.device: " + spinalConfig.device)
    spinalConfig.generateVerilog(new Ram2Ports(ClockDomain.current, spinalConfig.device, width bits, length bits))
  }
}
//Generate the MyTopLevel's Verilog using the above custom configuration.
object MyTopLevelVerilogWithCustomConfig extends Ram2PortConfig {
  def main(args: Array[String]) {
    generateRam2Port(AlteraConfig, 16, 12)
  }
}