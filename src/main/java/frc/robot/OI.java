/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public final class OI {
  private static final int X = 3;

  public static final class Controllers {
    public static Joystick leftJoystick = new Joystick(1);
    public static Joystick rightJoystick = new Joystick(0);
    public static XboxController gamepad = new XboxController(2);
  }

  public static final class Buttons {
    public static final Button abortMacroPrimary = new JoystickButton(Controllers.rightJoystick, 2);
    public static final Button toggleActuator = new JoystickButton(Controllers.gamepad, X);
    public static final Button toggleCompressor = new JoystickButton(Controllers.rightJoystick, 3);
    public static final Button togglePipeline = new JoystickButton(Controllers.rightJoystick, 5);
    public static final Button alignRobot = new JoystickButton(Controllers.leftJoystick, 1);
    public static final Button moveForward = new JoystickButton(Controllers.leftJoystick, 2);

    public static final Button toggleFrontPistons = new JoystickButton(Controllers.gamepad, 5);
    public static final Button toggleBackPistons = new JoystickButton(Controllers.gamepad, 6);

    
  }

  // Private constructor to prevent instantiation
  private OI() {
  }
}
