package memory

import spinal.core.{BitCount, BlackBox, Bundle, ClockDomain, Generic, IntToBuilder, in, out}

//noinspection ScalaUnusedSymbol,SpellCheckingInspection
class AltSyncRam(addressRegB: String,
                 clockEnableInputA: String,
                 clockEnableInputB: String,
                 clockEnableOutputA: String,
                 clockEnableOutputB: String,
                 inDataRegB: String,
                 intendedDeviceFamily: String,
                 lpmType: String,
                 numWordsA: Integer,
                 numWordsB: Integer,
                 operationMode: String,
                 outDataAclrA: String,
                 outDataAclrB: String,
                 outDataRegA: String,
                 outDataRegB: String,
                 powerUpUninitialized: String,
                 readDuringWriteModeMixedPorts: String,
                 readDuringWriteModePortA: String,
                 readDuringWriteModePortB: String,
                 widthAddressA: BitCount,
                 widthAddressB: BitCount,
                 widthA: BitCount,
                 widthB: BitCount,
                 widthByteEnableA: Integer,
                 widthByteEnableB: Integer,
                 wrControlWrAddressRegB: String,
                 AClockDomain: ClockDomain,
                 BClockDomain: ClockDomain
                ) extends BlackBox {
  val generic = new Generic {
    val address_reg_b                      = AltSyncRam.this.addressRegB
    val clock_enable_input_a               = AltSyncRam.this.clockEnableInputA
    val clock_enable_input_b               = AltSyncRam.this.clockEnableInputB
    val clock_enable_output_a              = AltSyncRam.this.clockEnableOutputA
    val clock_enable_output_b              = AltSyncRam.this.clockEnableOutputB
    val indata_reg_b                       = AltSyncRam.this.inDataRegB
    val intended_device_family             = AltSyncRam.this.intendedDeviceFamily
    val lpm_type                           = AltSyncRam.this.lpmType
    val numwords_a                         = AltSyncRam.this.numWordsA
    val numwords_b                         = AltSyncRam.this.numWordsB
    val operation_mode                     = AltSyncRam.this.operationMode
    val outdata_aclr_a                     = AltSyncRam.this.outDataAclrA
    val outdata_aclr_b                     = AltSyncRam.this.outDataAclrB
    val outdata_reg_a                      = AltSyncRam.this.outDataRegA
    val outdata_reg_b                      = AltSyncRam.this.outDataRegB
    val power_up_uninitialized             = AltSyncRam.this.powerUpUninitialized
    val read_during_write_mode_mixed_ports = AltSyncRam.this.readDuringWriteModeMixedPorts
    val read_during_write_mode_port_a      = AltSyncRam.this.readDuringWriteModePortA
    val read_during_write_mode_port_b      = AltSyncRam.this.readDuringWriteModePortB
    val widthad_a                          = AltSyncRam.this.widthAddressA.value
    val widthad_b                          = AltSyncRam.this.widthAddressB.value
    val width_a                            = AltSyncRam.this.widthA.value
    val width_b                            = AltSyncRam.this.widthB.value
    val width_byteena_a                    = AltSyncRam.this.widthByteEnableA
    val width_byteena_b                    = AltSyncRam.this.widthByteEnableB
    val wrcontrol_wraddress_reg_b          = AltSyncRam.this.wrControlWrAddressRegB
  }

  val io = new Bundle {
    val address_a = in UInt widthAddressA
    val address_b = in UInt widthAddressB
    val clock0 = in Bool
    val data_a = in Bits widthA
    val data_b = in Bits widthB
    val wren_a = in Bool
    val wren_b = in Bool
    val q_a = out Bits widthA
    val q_b = out Bits widthB
    val aclr0 = in Bool
    val aclr1 = in Bool
    val addressstall_a = in Bool
    val addressstall_b = in Bool
    val byteena_a = in Bool
    val byteena_b = in Bool
    val clock1 = in Bool
    val clocken0 = in Bool
    val clocken1 = in Bool
    val clocken2 = in Bool
    val clocken3 = in Bool
    val eccstatus = in UInt(3 bits)
    val rden_a = in Bool
    val rden_b = in Bool
  }

  mapClockDomain(AClockDomain, io.clock0)
  mapClockDomain(BClockDomain, io.clock1)
  noIoPrefix()
}

altsyncram	altsyncram_component (
.address_a (address_a),
.address_b (address_b),
.clock0 (clock),
.data_a (data_a),
.data_b (data_b),
.wren_a (wren_a),
.wren_b (wren_b),
.q_a (sub_wire0),
.q_b (sub_wire1),
.aclr0 (1'b0),
.aclr1 (1'b0),
.addressstall_a (1'b0),
.addressstall_b (1'b0),
.byteena_a (1'b1),
.byteena_b (1'b1),
.clock1 (1'b1),
.clocken0 (1'b1),
.clocken1 (1'b1),
.clocken2 (1'b1),
.clocken3 (1'b1),
.eccstatus (),
.rden_a (1'b1),
.rden_b (1'b1));
defparam
altsyncram_component.address_reg_b = "CLOCK0",
altsyncram_component.clock_enable_input_a = "BYPASS",
altsyncram_component.clock_enable_input_b = "BYPASS",
altsyncram_component.clock_enable_output_a = "BYPASS",
altsyncram_component.clock_enable_output_b = "BYPASS",
altsyncram_component.indata_reg_b = "CLOCK0",
altsyncram_component.intended_device_family = "Cyclone V",
altsyncram_component.lpm_type = "altsyncram",
altsyncram_component.numwords_a = LENGTH_EXP,
altsyncram_component.numwords_b = LENGTH_EXP,
altsyncram_component.operation_mode = "BIDIR_DUAL_PORT",
altsyncram_component.outdata_aclr_a = "NONE",
altsyncram_component.outdata_aclr_b = "NONE",
altsyncram_component.outdata_reg_a = "CLOCK0",
altsyncram_component.outdata_reg_b = "CLOCK0",
altsyncram_component.power_up_uninitialized = "FALSE",
altsyncram_component.read_during_write_mode_mixed_ports = "DONT_CARE",
altsyncram_component.read_during_write_mode_port_a = "NEW_DATA_NO_NBE_READ",
altsyncram_component.read_during_write_mode_port_b = "NEW_DATA_NO_NBE_READ",
altsyncram_component.widthad_a = LENGTH,
altsyncram_component.widthad_b = LENGTH,
altsyncram_component.width_a = WIDTH,
altsyncram_component.width_b = WIDTH,
altsyncram_component.width_byteena_a = 1,
altsyncram_component.width_byteena_b = 1,
altsyncram_component.wrcontrol_wraddress_reg_b = "CLOCK0";
