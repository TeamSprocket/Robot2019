/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public final class RobotMap {
  public static final class Drivetrain {
    public static final int FRONT_LEFT_TALON = 3;
    public static final int FRONT_RIGHT_TALON = 4;
    public static final int BACK_LEFT_TALON = 0;
    public static final int BACK_RIGHT_TALON = 2;
  }

  public static final class Arm {
    public static final int POT = 0;
  }

  public static final class CargoShooter {
    public static final int LEFT_TALON = 1;
    public static final int RIGHT_TALON = 5;
  }

  public static final class HatchActuator {
    public static final int PISTON_FORWARD = 4;
    public static final int PISTON_REVERSE = 5;
  }

  // Private constructor to prevent instantiation
  private RobotMap() {
  }
}
