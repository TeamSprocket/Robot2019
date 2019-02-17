/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * An improved AnalogPotentiometer with a multitude of additional
 * functionalities, such as calculating the rate in which its angle is changing
 * and dynamically changing the offset.
 */
public class ChickenPotPie extends AnalogPotentiometer {
  private static final List<ChickenPotPie> pies = new ArrayList<>();
  
  private double offset;

  public static void updateAll() {
    for (ChickenPotPie p : pies) {
      p.update();
    }
  }

  private double previous, lastUpdatePiestamp;

  public ChickenPotPie(AnalogInput input) {
    super(input);
    pies.add(this);
  }

  public ChickenPotPie(AnalogInput input, double scale) {
    super(input, scale);
    pies.add(this);
  }

  public ChickenPotPie(AnalogInput input, double fullRange, double offset) {
    super(input, fullRange, offset);
    pies.add(this);
    this.offset = offset;
  }

  public ChickenPotPie(int channel) {
    super(channel);
    pies.add(this);
  }

  public ChickenPotPie(int channel, double scale) {
    super(channel, scale);
    pies.add(this);
  }

  public ChickenPotPie(int channel, double fullRange, double offset) {
    super(channel, fullRange);
    pies.add(this);
    this.offset = offset;
  }

  private void update() {
    previous = get();
    lastUpdatePiestamp = System.nanoTime();
  }
  
  public void setOffset(double offset) {
    this.offset = offset;
  }

  public double getOffset() {
    return offset;
  }

  public double get() {
    return super.get() + offset;
  }
  
  public double getRate() {
    return (get() - previous) / ((System.nanoTime() - lastUpdatePiestamp) / 1e9);
  }
}
