// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // Create CANSparkMax objects to control the motors on the drivetrain.
  CANSparkMax motorFrontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
  CANSparkMax motorFrontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
  CANSparkMax motorRearLeft = new CANSparkMax(Constants.DRIVE_REAR_LEFT, MotorType.kBrushless);
  CANSparkMax motorRearRight = new CANSparkMax(Constants.DRIVE_REAR_RIGHT, MotorType.kBrushless);

  // Create MotorControllerGroup objects to use the defined motors in a DifferentialDrive class.
  MotorControllerGroup motorsLeft = new MotorControllerGroup(motorFrontLeft, motorRearLeft);
  MotorControllerGroup motorsRight = new MotorControllerGroup(motorFrontRight, motorRearRight);

  // Create a DifferentialDrive object with the defined motors.
  DifferentialDrive drive = new DifferentialDrive(motorsLeft, motorsRight);
  
  
  /** Creates a new Drivetrain */
  public Drivetrain() {
    // Set all motors to Brake mode.
    motorFrontLeft.setIdleMode(IdleMode.kBrake);
    motorFrontRight.setIdleMode(IdleMode.kBrake);
    motorRearLeft.setIdleMode(IdleMode.kBrake);
    motorRearRight.setIdleMode(IdleMode.kBrake);

    // Invert the right motors.
    motorFrontRight.setInverted(true);
    motorRearRight.setInverted(true);
  }

  /**
   * ArcadeDrive - drivetrain control with "arcade" controls.
   * 
   * Left Y-axis controls throttle, Right X-axis controls rotation.
   */
  public void arcadeDrive(double throttle, double rotation) {
    // Call DifferentialDrive's arcadeDrive method.
    drive.arcadeDrive(throttle, rotation);
  }
  
  /**
   * TankDrive - drivetrain control with "tank" controls.
   * 
   * Left Y-axis controls the left side, right Y-axis controls the right side.
   */
  public void tankDrive(double left, double right) {
    // Call DifferentialDrive's tankDrive method.
    drive.tankDrive(left, right);
  }

  /**
   * CurvatureDrive - drivetrain control with "curvature" or "cheesy" controls.
   * 
   * Changing the rotation modifies the rate of heading, making the drivetrain
   * operate more like a car.
   * 
   * Left Y-axis controls throttle, Right X-axis controls rotation.
   */
  public void curvatureDrive(double throttle, double rotation) {
    // Call DifferentialDrive's curvatureDrive method.
    drive.curvatureDrive(throttle, rotation, true);
  }

  /**
   * StopDrive - stops all motors on the drivetrain.
   */
  public void stopDrive() {
    drive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
