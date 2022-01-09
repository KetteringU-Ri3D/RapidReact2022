// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ClimbDown extends CommandBase {
  // Create an object for the climber.
  private final Climber m_climber;

  // Create a DoubleSupplier for the power being applied to the motor.
  private final DoubleSupplier m_power;

  /** Creates a new ClimbDown. 
   * 
   * @param climber The subsystem used by this command.
  */
  public ClimbDown(Climber climber, DoubleSupplier power) {
    // Use the Climber subsystem to gain access to its commands.
    m_climber = climber;

    // Apply power to the motor.
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.climbDown(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
