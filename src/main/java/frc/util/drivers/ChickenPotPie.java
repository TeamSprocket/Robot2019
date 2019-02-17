/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.drivers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * An AnalogPotentiometer with the additional functionality of calculating the
 * rate in which its angle is changing
 */
public class ChickenPotPie extends AnalogPotentiometer {
  private static final List<ChickenPotPie> pies = new ArrayList<>();

  public static void updateAll() {
    for (ChickenPotPie p : pies) {
      p.updatePie();
    }
  }

  private double previousPie, lastUpdatePiestamp;

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
    super(channel, fullRange, offset);
    pies.add(this);
  }

  private void updatePie() {
    previousPie = get();
    lastUpdatePiestamp = System.nanoTime();
  }

  public double getRate() {
    return (get() - previousPie) / ((System.nanoTime() - lastUpdatePiestamp) / 1e9);
  }
}
