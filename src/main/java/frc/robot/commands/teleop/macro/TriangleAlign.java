/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.teleop.macro.MoveForward;

public class TriangleAlign extends CommandGroup {
  private final double h1 = 0.65;
  private final double h2 = 0.85;
  private final double theta1 = -10;
  private double tx, theta2, d, r, distance; // in degrees and meters
  public TriangleAlign() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    theta2 = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    d = (h2 - h1)/(Math.toDegrees(Math.tan(theta1 + theta2)));
    r = d/Math.toDegrees(Math.cos(tx));
    distance = r * Math.toDegrees(Math.sin(tx))/Math.toDegrees(Math.sin(180 - 2 * tx));
    addSequential(new PIDTurn(tx * 2));
    addSequential(new MoveForward(distance));
    addSequential(new PIDTurn(tx * -2));
    addSequential(new Align());
  }

// Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void interrupted() {
    System.out.println("Interrupted!");
  }
}
