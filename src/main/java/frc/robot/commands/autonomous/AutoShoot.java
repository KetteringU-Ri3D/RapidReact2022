// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.group.CollectAndIndex;
import frc.robot.commands.shooter.SpinUp;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShoot extends ParallelDeadlineGroup {
  /** Creates a new AutoShoot. */
  public AutoShoot(Shooter shooter, Indexer indexer, Intake intake) {
    // Add the deadline command in the super() call. Add other commands using
    // addCommands().
    super(new WaitCommand(4));
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SpinUp(shooter, () -> 0.85),
      new CollectAndIndex(intake, indexer, 0.65, 0.4)
      // new WaitCommand(5)
    );
  }
}
