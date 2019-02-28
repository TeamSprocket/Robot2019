/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A Subsystem used to easily obtain values from the Limelight.
 * 
 * TODO: Refactor existing commands utilizing vision to use this subsystem.
 */
public final class Limelight extends Subsystem {
  private static enum Pipeline {
    DRIVER, VISION
  }

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

  public void setPipeline(Pipeline pipeline) {
    switch(pipeline) {
      case DRIVER:
        limelightTable.getEntry("pipeline").setNumber(1);
        break;
      default:
        limelightTable.getEntry("pipeline").setNumber(0);
    }
    DriverStation.reportWarning("Switched to pipeline " + pipeline + "!", false);
  }

  public void togglePipleine() {
    if(limelightTable.getEntry("pipeline").getDouble(0) == 0)
      setPipeline(Pipeline.DRIVER);
    else
      setPipeline(Pipeline.VISION);
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
