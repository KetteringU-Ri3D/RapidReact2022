// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

/** Arcade-style drive control. 
 *  Y-axis of the left stick controls speed, 
 *  X-axis of the right stick controls rotation
 */
public class TankDrive extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;
  private final DoubleSupplier m_throttle, m_rotation;

  /**
   * Creates a new TankDrive.
   *
   * @param drivetrain The subsystem used by this command.
   */
  public TankDrive(Drivetrain drivetrain, DoubleSupplier throttle, DoubleSupplier rotation) {
    // Set the drivetrain argument to the Drivetrain subsystem.
    m_drivetrain = drivetrain;

    // Set the throttle and rotation to those being modified in this command.
    m_throttle = throttle;
    m_rotation = rotation;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Use the tankDrive method from the Drivetrain subsystem.
    m_drivetrain.tankDrive(m_throttle.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Use the stopDrive method from the Drivetrain subsystem.
    m_drivetrain.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
