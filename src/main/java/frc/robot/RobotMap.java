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
    public static final int FRONT_LEFT_TALON = 2;
    public static final int MID_LEFT_TALON = 3;
    public static final int BACK_LEFT_TALON = 4;
    public static final int FRONT_RIGHT_VICTOR = 7;
    public static final int MID_RIGHT_TALON = 6;
    public static final int BACK_RIGHT_TALON = 5;

    public static final int RIGHT_ENCODER_A = 2;
    public static final int RIGHT_ENCODER_B = 3;
    public static final int LEFT_ENCODER_A = 4;
    public static final int LEFT_ENCODER_B = 5;

    private Drivetrain() {
    }
  }

  public static final class Arm {
    public static final int ARM_TALON = 9;

    public static final int POT = 0;
    public static final int FRONT_LIMIT_SWITCH = 0;
    public static final int BACK_LIMIT_SWITCH = 1;
    
    private Arm() {
    }
  }

  public static final class CargoShooter {
    public static final int LEFT_TALON = 1;
    public static final int RIGHT_VICTOR = 8;

    private CargoShooter() {
    }
  }

  public static final class HatchActuator {
    public static final int CONE_PISTON_FORWARD = 2;
    public static final int CONE_PISTON_REVERSE = 5; //2;
    public static final int ACTUATOR_PISTON_FORWARD = 4; //0;
    public static final int ACTUATOR_PISTON_REVERSE = 3;

    private HatchActuator() {
    }
  }
  
  public static final class HabClimb {
    public static final int FRONT_FORWARD = 6;
    public static final int FRONT_REVERSE = 1;
    public static final int BACK_FORWARD = 7;
    public static final int BACK_REVERSE = 0;

    public static final int FRONT_SWITCH = 0;
    public static final int BACK_SWITCH = 1;
    
    private HabClimb() {
    }
  }

  // Private constructor to prevent instantiation
  private RobotMap() {
  }
}
