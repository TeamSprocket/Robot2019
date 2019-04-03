/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auton.routine;

import java.beans.PersistenceDelegate;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.instant.ToggleBackPistons;
import frc.robot.commands.instant.ToggleFrontPistons;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HabClimb;
import frc.util.commands.teleop.persistent.PersistentCommand;

public class Climb extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Climb() {
    addSequential(new ToggleFrontPistons());
    // addSequential(new DriveUntil(HabClimb.get()::frontSwitchPressed));
    addSequential(new ToggleFrontPistons());
    addSequential(new ToggleBackPistons());
    // addSequential(new DriveUntil(HabClimb.get()::backSwitchPressed));
    addSequential(new ToggleBackPistons());
  }

  private static class DriveUntil extends Command {
    private BooleanSupplier bs;

    public DriveUntil(BooleanSupplier bs) {
      requires(Drivetrain.get());

      this.bs = bs;
    }

    @Override
    protected void execute() {
      Drivetrain.get().arcadeDrive(0.1, 0);
    }

    @Override
    protected boolean isFinished() {
      return bs.getAsBoolean();
    }

    @Override
    protected void end() {
      PersistentCommand.startPersistent(Drivetrain.get());
    }

    @Override
    protected void interrupted() {
      PersistentCommand.startPersistent(Drivetrain.get());
    }
  }
}
