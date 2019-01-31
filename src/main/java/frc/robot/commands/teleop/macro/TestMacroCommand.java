/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.macro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.macro.MacroCommand;

public class TestMacroCommand extends MacroCommand {
  public TestMacroCommand() {
    requires(Robot.test);
  }

  @Override
  protected void initialize() {
    setTimeout(1);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    if(isTimedOut())
      SmartDashboard.putBoolean("MC", false);
    return isTimedOut();
  }
}
