// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  // Create a TalonSRX object to control the motor on the intake.
  TalonSRX motor = new TalonSRX(Constants.INTAKE);

  // Create two DoubleSolenoid objects to control the solenoids on the intake.
  DoubleSolenoid solenoidLeft = new DoubleSolenoid(
    PneumaticsModuleType.REVPH, 
    Constants.INTAKE_SOLENOID_LEFT[0], 
    Constants.INTAKE_SOLENOID_LEFT[1]);

  DoubleSolenoid solenoidRight = new DoubleSolenoid(
    PneumaticsModuleType.REVPH, 
    Constants.INTAKE_SOLENOID_RIGHT[0], 
    Constants.INTAKE_SOLENOID_RIGHT[1]);

  /** Creates a new Intake. */
  public Intake() {}

  /**
   * collect - gather Cargo.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void collect(double power) {
    motor.set(ControlMode.PercentOutput, power);
  }

  /**
   * eject - remove Cargo.
   * 
   * @param power Voltage being applied to the motors.
   */
  public void eject(double power) {
    motor.set(ControlMode.PercentOutput, -power);
  }

  /**
   * stop - stop the motor.
   */
  public void stop() {
    motor.set(ControlMode.PercentOutput, 0);
  }

  /**
   * extend - extend the intake solenoids for collecting Cargo.
   */
  public void extend() {
    solenoidLeft.set(Value.kForward);
    solenoidRight.set(Value.kForward);
  }

  /**
   * retract - pull the intake solenoids back into the robot.
   */
  public void retract() {
    solenoidLeft.set(Value.kReverse);
    solenoidRight.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
