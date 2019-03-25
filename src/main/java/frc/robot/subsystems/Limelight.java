/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A Subsystem used to easily obtain values from the Limelight.
 */
public final class Limelight extends Subsystem {
  private final NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  private Limelight() {
  }

  public boolean targetVisible() {
    return limelightTable.getEntry("tv").getDouble(0) == 1;
  }

  public double getTx() {
    return limelightTable.getEntry("tx").getDouble(0);
  }

  public double getTy() {
    return limelightTable.getEntry("ty").getDouble(0);
  }

  public double getTa() {
    return limelightTable.getEntry("ta").getDouble(0);
  }

  public static final double CAMERA_HEIGHT = 34.6, TARGET_HEIGHT = 29;
  public static final double CAMERA_ANGLE = 0;
  public double getDistanceEstimate() {
    return (CAMERA_HEIGHT - TARGET_HEIGHT) * Math.tan(Math.toRadians(90 + getTy()));
  }

  public void setVisionMode(boolean vision){    
    if(vision)
      limelightTable.getEntry("pipeline").setNumber(1);
    else
      limelightTable.getEntry("pipeline").setNumber(0);
  }

  @Override
  public void initDefaultCommand() {
  }

  // Singleton instance and getter
  private static final Limelight instance = new Limelight();

	public static Limelight get() {
		return instance;
  }
}
