/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton.routine;

import frc.robot.commands.auton.MoveForwardTime;
import frc.robot.commands.auton.routine.AutonRoutineFactory.StartLevel;
import frc.robot.commands.instant.SetArm;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.util.commands.auton.routine.AutonRoutine;
import frc.util.units.angle.Degree;

public class KickOff extends AutonRoutine {
  public KickOff(StartLevel startLevel) {
    requires(Drivetrain.get());
    requires(Arm.get());

    if(startLevel == StartLevel.LEVEL_2)
      addSequential(new MoveForwardTime(1.5));
    else
      addSequential(new MoveForwardTime(1));

    addParallel(new SetArm(new Degree(90)));
  }
}
