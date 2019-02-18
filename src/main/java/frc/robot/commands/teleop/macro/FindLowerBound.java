/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Arm;
import frc.util.commands.teleop.macro.MacroCommand;

public class FindLowerBound extends MacroCommand {
  private double speed = 0;
  private double angle = 0;
  
  public FindLowerBound() {
    requires(Arm.get());
    SmartDashboard.putNumber("Starting Value", 0);
  }

  @Override
  protected void initialize() {
    speed = SmartDashboard.getNumber("Starting Value", 0);
    angle = Arm.get().getPot().get();
  }

  @Override
  protected void execute() {
    Arm.get().setSpeed(speed);
    SmartDashboard.putNumber("Actual speed", speed);
    speed -= 0.0001;

    SmartDashboard.putBoolean("MoveArm Running", false);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(Arm.get().getPot().get() - angle) > 5;
  }

  @Override
  protected void terminate() {

  }
}
