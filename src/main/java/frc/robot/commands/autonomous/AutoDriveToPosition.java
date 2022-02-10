// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutoDriveToPosition extends CommandBase {
  // Create an object for the drivetrain.
  private final Drivetrain m_drivetrain;

  // Create DoubleSuppliers for the setpoint and power.
  private final DoubleSupplier m_setpoint;
  private final DoubleSupplier m_power;
  
  /** Creates a new AutonomousDriveForward. */
  public AutoDriveToPosition(Drivetrain drivetrain, DoubleSupplier setpoint, DoubleSupplier power) {
    // Use the Drivetrain subsystem to gain access to its commands.
    m_drivetrain = drivetrain;

    // Set the setpoint and power.
    m_setpoint = setpoint;
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Reset the encoders.
    m_drivetrain.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Call the driveToPosition method.
    m_drivetrain.driveToPosition(m_setpoint.getAsDouble(), m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // Print a statement for end of auto.
    System.out.println("AUTO DONE");
    return false;
  }
}
