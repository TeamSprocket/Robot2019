/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.commands.teleop.persistent;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * A PersistentCommand is a Command that is continuously run during the
 * teleoperated period, but may be temporarily suspended during the execution
 * of a MacroCommand.
 * 
 * PersistentCommands must override only the execute method.
 */
public abstract class PersistentCommand extends Command {
  private static Map<Subsystem, PersistentCommand> persistentMap = new HashMap<>();

  public static void bindPersistent(PersistentCommand pc, Subsystem s) {
    persistentMap.put(s, pc);
  }

  public static void startPersistent(Subsystem subsystem) {
    persistentMap.get(subsystem).start();
  }

  public static void startAllPersistent() {
    for(PersistentCommand pc : persistentMap.values()) {
      pc.start();
    }
  }

  @Override
  protected final void initialize() {
  }

  @Override
  protected abstract void execute();

  @Override
  protected final boolean isFinished() {
    return false;
  }
  
  @Override
  protected final void end() {
  }

  @Override
  protected final void interrupted() {
  }
}
