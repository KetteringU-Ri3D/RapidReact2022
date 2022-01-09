// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  // Create TalonFX objects to control the motors on the shooter.
  TalonFX motorLeft = new TalonFX(Constants.SHOOTER_LEFT);
  TalonFX motorRight = new TalonFX(Constants.SHOOTER_RIGHT);

  /** Creates a new Shooter. */
  public Shooter() {
    // Set all motors to Coast mode.
    motorLeft.setNeutralMode(NeutralMode.Coast);
    motorRight.setNeutralMode(NeutralMode.Coast);

    // Invert the right motor.
    motorRight.setInverted(true);
  }

  /**
   * spinUp - spin the motors to prepare to shoot the Cargo.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void spinUp(double power) {
    // Apply the voltage to both motors. Note that the right motor is inverted.
    motorLeft.set(ControlMode.PercentOutput, -power);
    motorRight.set(ControlMode.PercentOutput, -power);
  }

  /**
   * spinDown - spin the motors to move the Cargo back into the robot.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void spinDown(double power) {
    // Apply the voltage to both motors. Note that the right motor is inverted.
    motorLeft.set(ControlMode.PercentOutput, power);
    motorRight.set(ControlMode.PercentOutput, power);
  }

  /**
   * stop - stop the shooter motors.
   */
  public void stop() {
    // Apply the voltage to both motors. Note that the right motor is inverted.
    motorLeft.set(ControlMode.PercentOutput, 0);
    motorRight.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
