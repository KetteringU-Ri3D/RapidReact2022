// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  // Create CANSparkMax objects to control the motors on the drivetrain.
  CANSparkMax m_motorFrontLeft = new CANSparkMax(Constants.DRIVE_FRONT_LEFT, MotorType.kBrushless);
  CANSparkMax m_motorRearLeft = new CANSparkMax(Constants.DRIVE_REAR_LEFT, MotorType.kBrushless);
  CANSparkMax m_motorFrontRight = new CANSparkMax(Constants.DRIVE_FRONT_RIGHT, MotorType.kBrushless);
  CANSparkMax m_motorRearRight = new CANSparkMax(Constants.DRIVE_REAR_RIGHT, MotorType.kBrushless);

  // Create MotorControllerGroup objects to use the defined motors in a DifferentialDrive class.
  MotorControllerGroup m_motorsLeft = new MotorControllerGroup(m_motorFrontLeft, m_motorRearLeft);
  MotorControllerGroup m_motorsRight = new MotorControllerGroup(m_motorFrontRight, m_motorRearRight);

  // Create a DifferentialDrive object with the defined motors.
  DifferentialDrive m_drive = new DifferentialDrive(m_motorsLeft, m_motorsRight);

  // Create RelativeEncoder objects for each of the defined motors.
  RelativeEncoder m_encoderFrontLeft, m_encoderRearLeft, m_encoderFrontRight, m_encoderRearRight;

  // Create SparkMaxPIDController objects for closed-loop control during autonomous.
  SparkMaxPIDController m_pidFrontLeft, m_pidRearLeft, m_pidFrontRight, m_pidRearRight;

  // Create variables for PID control values.
  double kP = 0.5, 
         kI = 0.0,
         kD = 0.125, 
         kIz = 0.0, 
         kFF = 0.0, 
         kMaxOutput = 1, 
         kMinOutput = -1;
  
  
  /** Creates a new Drivetrain */
  public Drivetrain() {
    // Restore all factory defaults for safety.
    m_motorFrontLeft.restoreFactoryDefaults();
    m_motorRearLeft.restoreFactoryDefaults();
    m_motorFrontRight.restoreFactoryDefaults();
    m_motorRearRight.restoreFactoryDefaults();

    // Set all motors to Brake mode.
    m_motorFrontLeft.setIdleMode(IdleMode.kCoast);
    m_motorFrontRight.setIdleMode(IdleMode.kCoast);
    m_motorRearLeft.setIdleMode(IdleMode.kCoast);
    m_motorRearRight.setIdleMode(IdleMode.kCoast);

    // Invert the right motors.
    m_motorFrontRight.setInverted(true);
    m_motorRearRight.setInverted(true);

    // Allow m_motors to be used with PID control.
    m_pidFrontLeft = m_motorFrontLeft.getPIDController();
    m_pidRearLeft = m_motorRearLeft.getPIDController();
    m_pidFrontRight = m_motorFrontRight.getPIDController();
    m_pidRearRight = m_motorRearRight.getPIDController();

    // Set the encoders to their respective motors.
    m_encoderFrontLeft = m_motorFrontLeft.getEncoder();
    m_encoderRearLeft = m_motorRearLeft.getEncoder();
    m_encoderFrontRight = m_motorFrontRight.getEncoder();
    m_encoderRearRight = m_motorRearRight.getEncoder();

    // Set the feedback device of each PID controller to its respective encoder.
    m_pidFrontLeft.setFeedbackDevice(m_encoderFrontLeft);
    m_pidRearLeft.setFeedbackDevice(m_encoderRearLeft);
    m_pidFrontRight.setFeedbackDevice(m_encoderFrontRight);
    m_pidRearRight.setFeedbackDevice(m_encoderRearRight);

    // Set PID coefficients.
    setCoefficients(m_pidFrontLeft);
    setCoefficients(m_pidRearLeft);
    setCoefficients(m_pidFrontRight);
    setCoefficients(m_pidRearRight);
  }

  /**
   * arcadeDrive - drivetrain control with "arcade" controls.
   * 
   * Left Y-axis controls throttle, Right X-axis controls rotation.
   * 
   * @param throttle Forward/backward speed of the motors
   * @param rotation Rotational speed of the motors
   */
  public void arcadeDrive(double throttle, double rotation) {
    // Call DifferentialDrive's arcadeDrive method.
    m_drive.arcadeDrive(throttle, rotation, true);
  }
  
  /**
   * tankDrive - drivetrain control with "tank" controls.
   * 
   * Left Y-axis controls the left side, right Y-axis controls the right side.
   * 
   * @param left Voltage applied to the left motors.
   * @param right Voltage applied to the right motors.
   */
  public void tankDrive(double left, double right) {
    // Call DifferentialDrive's tankDrive method.
    m_drive.tankDrive(left, right);
  }

  /**
   * curvatureDrive - drivetrain control with "curvature" or "cheesy" controls.
   * 
   * Changing the rotation modifies the rate of heading, making the drivetrain
   * operate more like a car.
   * 
   * Left Y-axis controls throttle, Right X-axis controls rotation.
   * 
   * @param throttle Forward/backward speed of the motors
   * @param rotation Rotational speed of the motors
   */
  public void curvatureDrive(double throttle, double rotation) {
    // Call DifferentialDrive's curvatureDrive method.
    m_drive.curvatureDrive(throttle, rotation, true);
  }

  /**
   * stopDrive - stops all motors on the drivetrain.
   */
  public void stop() {
    // Call CANSparkMax's stopMotor.
    m_drive.stopMotor();
  }

  /**
   * driveToPosition - use encoders to drive to a set position.
   * 
   * @param setpoint - ending position to drive to.
   * @param power - power applied to the drive motors.
   */
  public void driveToPosition(double setpoint, double power) {
    // Convert the setpoint to encoder counts.
    double counts = inchesToEncoderCounts(setpoint);

    // // Set the position of both front encoders.
    // m_encoderFrontLeft.setPosition(counts);
    // m_encoderFrontRight.setPosition(counts);

    // // Set the reference of each PID controller to the setpoint.
    // m_pidFrontLeft.setReference(counts, ControlType.kPosition);
    // m_pidFrontRight.setReference(counts, ControlType.kPosition);

    System.out.println("DRIVE ENCODER POSITION: " + m_encoderFrontLeft.getPosition());

    arcadeDrive(power, 0);
    if (m_encoderFrontLeft.getPosition() >= inchesToEncoderCounts(counts)) {
      arcadeDrive(0, 0);
    }    
  }

  /**
   * resetEncoders - reset the encoder values to make using them again easier.
   */
  public void resetEncoders() {
    // Set the position of both front encoders to zero, effectively resetting them.
    m_encoderFrontLeft.setPosition(0);
    m_encoderFrontRight.setPosition(0);
  }

  /**
   * setCoefficients - set the PID coefficients of the given PID controller.
   * 
   * @param controller - the given PID controller.
   */
  public void setCoefficients(SparkMaxPIDController controller) {
    controller.setP(kP);
    controller.setI(kI);
    controller.setD(kD);
    controller.setIZone(kIz);
    controller.setFF(kFF);
    controller.setOutputRange(kMinOutput, kMaxOutput);
  }

  /**
   * 
   * @param inch - the desired value in inches.
   */
  public double inchesToEncoderCounts(double inch) {
    // Set constant values.
    double wheelDiameter = 6;
    double encoderCountsPerRev = 4096;

    // Calculate the circumference.
    double circumference = wheelDiameter * Math.PI;

    // Calculate the encoder counts per inch.
    double countsPerInch = encoderCountsPerRev / circumference;

    // Calculate the final answer, which is the number of encoder counts required.
    double result = countsPerInch / inch;

    // Send the value back.
    return result;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
