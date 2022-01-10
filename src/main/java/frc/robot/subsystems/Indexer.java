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
  TalonSRX motorSide = new TalonSRX(Constants.INDEXER_SIDE);
  TalonSRX motorMid = new TalonSRX(Constants.INDEXER_MID);
  TalonSRX motorUp = new TalonSRX(Constants.INDEXER_UP);

  /** Creates a new Indexer. */
  public Indexer() {
    // Set all motors to Brake mode.
    motorSide.setNeutralMode(NeutralMode.Brake);
    motorMid.setNeutralMode(NeutralMode.Brake);
    motorUp.setNeutralMode(NeutralMode.Brake);
  }

  /**
   * index - spin all motors towards the shooter.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void index(double power) {
    // Apply voltage to the motors.
    motorSide.set(ControlMode.PercentOutput, power);
    motorMid.set(ControlMode.PercentOutput, power);
    motorUp.set(ControlMode.PercentOutput, power);
  }

  /**
   * outdex - spin all motors away the shooter.
   * 
   * @param power Voltage being applied to the motor.
   */
  public void outdex(double power) {
    // Apply voltage to the motors.
    motorSide.set(ControlMode.PercentOutput,-power);
    motorMid.set(ControlMode.PercentOutput, -power);
    motorUp.set(ControlMode.PercentOutput, -power);
  }

  public void stop() {
    // Apply no voltage to the motors.
    motorSide.set(ControlMode.PercentOutput, 0);
    motorMid.set(ControlMode.PercentOutput, 0);
    motorUp.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
