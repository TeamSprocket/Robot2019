/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;

/**
 * TODO: An AnalogPotentiometer 
 */
public class ChickenPotPie extends AnalogPotentiometer {
  public ChickenPotPie(AnalogInput input) {
    super(input);
  }

  public ChickenPotPie(AnalogInput input, double scale) {
    super(input, scale);
  }

  public ChickenPotPie(AnalogInput input, double fullRange, double offset) {
    super(input, fullRange, offset);
  }

  public ChickenPotPie(int channel) {
    super(channel);
  }

  public ChickenPotPie(int channel, double scale) {
    super(channel, scale);
  }

  public ChickenPotPie(int channel, double fullRange, double offset) {
    super(channel, fullRange, offset);
  }

  public double getRate() {
    return 0;
  }
}
