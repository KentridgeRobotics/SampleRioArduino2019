/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {

  private double x;
  private double y;
  private final DriveSubsystem subsystem;

  public DriveCommand(DriveSubsystem subsystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(subsystem);
    this.subsystem = subsystem;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.x = 0.0;
    this.y = 0.0;
  }

  public void setCommand(byte[] command) {
    String s = new String(command);
    System.err.println("COMMAND: "+s);
    String[] xyStrs = s.split(",");
    double xAbs =  (double) Integer.parseInt(xyStrs[0].trim());
    double yAbs = (double) Integer.parseInt(xyStrs[1].trim());
    this.x = (xAbs - 500.0) / 600.0;
    this.y = (yAbs - 500.0) / 600.0;
  } 

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    subsystem.setMotorSpeed((double) x, (double)y);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.x = 0;
    this.y = 0;
    subsystem.setMotorSpeed(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
