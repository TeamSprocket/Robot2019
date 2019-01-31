/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.teleop.persistent;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.commands.teleop.persistent.PersistentCommand;

public class TestPersistentCommand extends PersistentCommand {
  public TestPersistentCommand() {
    requires(Robot.test);
  }

  @Override
  protected void execute() {
    SmartDashboard.putBoolean("PC", true);
  }
}
