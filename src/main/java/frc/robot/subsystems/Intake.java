// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  // Create a VictorSPX object to control the motor on the intake.
  VictorSPX m_motor = new VictorSPX(Constants.INTAKE);

  /** Creates a new Intake. */
  public Intake() {
    // Put the motor in Coast mode.
    m_motor.setNeutralMode(NeutralMode.Coast);
  }

  /**
   * collect - gather Cargo.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void collect(double power) {
    // Apply voltage to the motor.
    m_motor.set(ControlMode.PercentOutput, power);
  }

  /**
   * eject - remove Cargo.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void eject(double power) {
    // Apply voltage to the motor.
    m_motor.set(ControlMode.PercentOutput, -power);
  }

  /**
   * stop - stop the motor.
   */
  public void stop() {
    // Apply no voltage to the motor.
    m_motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
