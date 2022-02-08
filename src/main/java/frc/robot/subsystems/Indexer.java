// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  // Create TalonSRX objects to control the motors on the indexer.
  TalonSRX m_motorSide = new TalonSRX(Constants.INDEXER_SIDE);
  TalonSRX m_motorMid = new TalonSRX(Constants.INDEXER_MID);
  TalonSRX m_motorUp = new TalonSRX(Constants.INDEXER_UP);
  TalonSRX m_motorTop = new TalonSRX(Constants.INDEXER_TOP);

  /** Creates a new Indexer. */
  public Indexer() {
    // Set all motors to Brake mode.
    m_motorSide.setNeutralMode(NeutralMode.Brake);
    m_motorMid.setNeutralMode(NeutralMode.Brake);
    m_motorUp.setNeutralMode(NeutralMode.Brake);
    m_motorTop.setNeutralMode(NeutralMode.Brake);

    // Invert the upward motion motors.
    m_motorTop.setInverted(true);
  }

  /**
   * index - spin all motors towards the shooter.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void index(double power) {
    // Apply voltage to the motors.
    m_motorSide.set(ControlMode.PercentOutput, power * 2);
    m_motorMid.set(ControlMode.PercentOutput, power);
    m_motorUp.set(ControlMode.PercentOutput, power);
    m_motorTop.set(ControlMode.PercentOutput, power * 1.75);
  }

  /**
   * outdex - spin all motors away the shooter.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void outdex(double power) {
    // Apply voltage to the motors.
    m_motorSide.set(ControlMode.PercentOutput,-power * 2);
    m_motorMid.set(ControlMode.PercentOutput, -power);
    m_motorUp.set(ControlMode.PercentOutput, -power);
    m_motorTop.set(ControlMode.PercentOutput, -power * 1.75);
  }

  public void stop() {
    // Apply no voltage to the motors.
    m_motorSide.set(ControlMode.PercentOutput, 0);
    m_motorMid.set(ControlMode.PercentOutput, 0);
    m_motorUp.set(ControlMode.PercentOutput, 0);
    m_motorTop.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
