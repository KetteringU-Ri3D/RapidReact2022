// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  // Create a TalonFX object to control the motor on the climber.
  TalonFX motor = new TalonFX(Constants.CLIMBER);

  /** Creates a new Climber. */
  public Climber() {}

  /**
   * raise - lifts the climber to prepare to hang.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void raise(double power) {
    // Apply voltage to the motor.
    motor.set(ControlMode.PercentOutput, -power);
  }

  /**
   * lower - drops the climber.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void lower(double power) {
    // Apply voltage to the motor.
    motor.set(ControlMode.PercentOutput, power);
  }

  /**
   * stop - stops the motor.
   */
  public void stop() {
    // Apply no voltage to the motor.
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
