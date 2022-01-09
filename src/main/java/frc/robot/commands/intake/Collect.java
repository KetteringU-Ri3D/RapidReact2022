// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class Collect extends CommandBase {
  // Create an object for the intake.
  private final Intake m_intake;

  // Create a DoubleSupplier for the power being applied to the motor.
  private final DoubleSupplier m_power;


  /** Creates a new Collect. */
  public Collect(Intake intake, DoubleSupplier power) {
    // Use the Intake subsystem to gain access to its commands.
    m_intake = intake;

    // Apply power to the motor.
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Use the collect method from the Intake subsystem.
    m_intake.collect(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Use the stop method from the Intake subsystem.
    m_intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
