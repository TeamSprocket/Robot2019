/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem used for controlling the arm
 */
public class Arm extends Subsystem {
    public static final int POT = 0;
    private final AnalogPotentiometer pot = new AnalogPotentiometer(POT, 720, 0);

    @Override
    protected void initDefaultCommand() {

    }
    public AnalogPotentiometer getPot() {
        return pot;
      }
  // Singleton instance, getter, and constructor
    private static final Arm instance = new Arm();

    public static Arm getInstance() {
        return instance;
  }
}
