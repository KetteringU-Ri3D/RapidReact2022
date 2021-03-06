// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.autonomous.AutoDriveToPosition;
import frc.robot.commands.autonomous.AutoShootThenDrive;
import frc.robot.commands.drivetrain.ArcadeDrive;
import frc.robot.commands.drivetrain.CurvatureDrive;
import frc.robot.commands.drivetrain.TankDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Shooter m_shooter = new Shooter();
  private final Intake m_intake = new Intake();
  private final Indexer m_indexer = new Indexer();
  private final Climber m_climber = new Climber();

  // Create an AutoDriveToPosition command to be used in autonomous.
  private final AutoDriveToPosition m_autoDriveToPosition = new AutoDriveToPosition(m_drivetrain, () -> 90, () -> 0.5);
  private final AutoShootThenDrive m_autoShootThenDrive = new AutoShootThenDrive(m_drivetrain, m_shooter, m_indexer, m_intake);

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
        () -> m_driveController.getLeftY() * 0.85,
        () -> m_driveController.getRightX() * 0.85
      )
    );

    // // Set the drive controls to tank.
    // m_drivetrain.setDefaultCommand(
    //   new TankDrive(
    //     m_drivetrain, 
    //     () -> m_driveController.getLeftY(),
    //     () -> m_driveController.getRightY()
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
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passingq it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Raise the climber when the A button is pressed.
    new JoystickButton(m_driveController, Button.kA.value)
      .whenPressed(() -> m_climber.raise(0.5))
      .whenReleased(() -> m_climber.stop());

    // Lower the climber when the B button is pressed.
    new JoystickButton(m_driveController, Button.kB.value)
      .whenPressed(() -> m_climber.lower(0.65))
      .whenReleased(() -> m_climber.stop());

    // Spin the shooter wheels up when the right bumper is pressed.
    new JoystickButton(m_driveController, Button.kRightBumper.value)
      .whenPressed(() -> m_shooter.spinUp(0.875))
      .whenReleased(() -> m_shooter.stop());

    // Run the intake and indexer inward when the left bumper is pressed.
    new JoystickButton(m_driveController, Button.kLeftBumper.value)
      .whenPressed(() -> collectAndIndex())
      .whenReleased(() -> stopCollectAndIndex());

    // Run the indexer outward when the Y button is pressed.
    new JoystickButton(m_driveController, Button.kY.value)
      .whenPressed(() -> spinDownAndOutdex())
      .whenReleased(() -> stopSpinDownAndOutdex());

    // Run the intake outward when the X button is pressed.
    new JoystickButton(m_driveController, Button.kX.value)
      .whenPressed(() -> m_intake.eject(0.65))
      .whenReleased(() -> m_intake.stop());
  }

  /**
   * collectAndIndex - run the collect and index commands.
   */
  public void collectAndIndex() {
    m_intake.collect(0.65);
    m_indexer.index(0.4);
  }

  /**
   * sotpCollectAndIndex - stop the collect and index commands.
   */
  public void stopCollectAndIndex() {
    m_intake.stop();
    m_indexer.stop();
  }

  /**
   * spinDownAndOutdex - run the spin down and outdex commands.
   */
  public void spinDownAndOutdex() {
    m_shooter.spinDown(0.1);
    m_indexer.outdex(0.4);
  }

  /**
   * stopCollectAndIndex - stop the collect and index commands.
   */
  public void stopSpinDownAndOutdex() {
    m_shooter.stop();
    m_indexer.stop();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // The chosen command will run in autonomous
    // return m_autoDriveToPosition;
    return m_autoShootThenDrive;
  }
}
