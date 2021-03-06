// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Define the port being used by the driver's controller.
    public static final int DRIVE_CONTROLLER = 0;

    // Define the CAN IDs being used by the drivetrain motors.
    public static final int DRIVE_FRONT_LEFT = 1;
    public static final int DRIVE_REAR_LEFT = 2;
    public static final int DRIVE_FRONT_RIGHT = 3;
    public static final int DRIVE_REAR_RIGHT = 4;

    // Define the CAN IDs being used by the shooter motors.
    public static final int SHOOTER_LEFT = 5;
    public static final int SHOOTER_RIGHT = 6;

    // Define the CAN ID being used by the climber motor.
    public static final int CLIMBER = 7;

    // Define the CAN ID being used by the intake motor.
    public static final int INTAKE = 8;

    // Define the CAN IDs being used by the indexer motors.
    public static final int INDEXER_SIDE = 9;
    public static final int INDEXER_MID = 10;
    public static final int INDEXER_UP = 11;
    public static final int INDEXER_TOP = 12;

    // Define the ports being used by the intake solenoids.
    public static final int [] INTAKE_SOLENOID_LEFT = new int[] {0, 1};
    public static final int [] INTAKE_SOLENOID_RIGHT = new int[] {2, 3};
}
