// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.autonomous.AutoDriveForward;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.CurvatureDrive;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();

  private final AutoDriveForward m_autoDriveForward = new AutoDriveForward();

  // Define an XboxController object to control the robot with.
  private final XboxController m_driveController = new XboxController(Constants.DRIVE_CONTROLLER);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings.
    configureButtonBindings();

    // Set the drive controls to arcade.
    m_drivetrain.setDefaultCommand(
      new ArcadeDrive(
        m_drivetrain, 
        () -> m_driveController.getLeftY(),
        () -> m_driveController.getRightX()
      )
    );

    // // Set the drive controls to tank.
    // m_drivetrain.setDefaultCommand(
    //   new TankDrive(
    //     m_drivetrain, 
    //     () -> m_driveController.getLeftY(),
    //     () -> m_driveController.getRightX()
    //   )
    // );

    // // Set the drive controls to curvature.
    // m_drivetrain.setDefaultCommand(
    //   new CurvatureDrive(
    //     m_drivetrain, 
    //     () -> m_driveController.getLeftY(),
    //     () -> m_driveController.getRightX()
    //   )
    // );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoDriveForward;
  }
}
