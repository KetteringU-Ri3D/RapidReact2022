// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class SpinUp extends CommandBase {
  // Create an object for the shooter.
  private final Shooter m_shooter;

  // Create a DoubleSupplier for the power being applied to the motors.
  private final DoubleSupplier m_power;

  /** Creates a new SpinUp. 
   * 
   * @param shooter The subsystem used by this command.
  */
  public SpinUp(Shooter shooter, DoubleSupplier power) {
    // Use the Shooter subsystem to gain access to its commands.
    m_shooter = shooter;

    // Apply power to the motors.
    m_power = power;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.spinUp(m_power.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
