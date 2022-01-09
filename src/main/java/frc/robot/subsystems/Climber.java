// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  // Create TalonFX object to control the motor on the climber.
  TalonFX motor = new TalonFX(Constants.CLIMBER);

  /** Creates a new Climber. */
  public Climber() {
    // Set motor to Coast mode.
    motor.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * climbUp - move the climber upward.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void climbUp(double power) {
    // Apply the voltage to the motor
    motor.set(ControlMode.PercentOutput, power);
  }

  /**
   * climbDown - move the climber downward.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void climbDown(double power) {
    // Apply the voltage to the motor
    motor.set(ControlMode.PercentOutput, -power);
  }

  /**
   * stop - stop the climber motor.
   */
  public void stop() {
    // Apply the voltage to the motor
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}