// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  // Create a CANSparkMax object to control the motor on the climber.
  CANSparkMax m_motor = new CANSparkMax(Constants.CLIMBER, MotorType.kBrushless);

  // Create a RelativeEncoder object for the defined motor.
  RelativeEncoder m_encoder;

  /** Creates a new Climber. */
  public Climber() {
    // Put the motor in brake mode.
    m_motor.setIdleMode(IdleMode.kBrake);

    // Set the encoder to motor.
    m_encoder = m_motor.getEncoder();
  }

  /**
   * raise - lifts the climber to prepare to hang.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void raise(double power) {
    // Apply voltage to the motor.
    m_motor.set(-power);

    // Read the encoder value.
    m_encoder.getPosition();
  }

  /**
   * lower - drops the climber.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void lower(double power) {
    // Apply voltage to the motor.
    m_motor.set(power);

    // Read the encoder value.
    m_encoder.getPosition();
  }

  /**
   * stop - stops the motor.
   */
  public void stop() {
    // Apply no voltage to the motor.
    m_motor.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
