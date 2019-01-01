/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.*;
public class Robot extends IterativeRobot {
  
	public static WPI_TalonSRX leftMain;
	public static WPI_TalonSRX rightMain;
	public static WPI_TalonSRX leftSlave;
	public static WPI_TalonSRX rightSlave;
	public static DifferentialDrive driveTrain;
	
	//Elevator/Manipulator
	public static WPI_TalonSRX elevator;
	public static WPI_TalonSRX intakeLeft;
	public static WPI_TalonSRX intake;


	private static XboxController driveStick;
	private static XboxController operateStick;
	private static Joystick testJoystick;

  @Override
  public void robotInit() {
    	leftMain = new WPI_TalonSRX(15);
		rightMain = new WPI_TalonSRX(1);
		leftSlave = new WPI_TalonSRX(14);
		rightSlave = new WPI_TalonSRX(2);
		leftSlave.follow(leftMain);
		rightSlave.follow(rightMain);
		testJoystick = new Joystick(0);
		//Elevator/Manipulator
		elevator = new WPI_TalonSRX(13);
		elevator.setInverted(true);
		intakeLeft = new WPI_TalonSRX(3);
		intake = new WPI_TalonSRX(4);
		intakeLeft.setInverted(true);
		intake.setInverted(true);

		driveStick = new XboxController(0);
		operateStick = new XboxController(1);

		driveTrain = new DifferentialDrive(leftMain,rightMain);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopPeriodic() {
	
	driveTrain.

	testJoystick.getAxis(0)		
	
	//Elevator
	if(operateStick.getXButton())
	{
		elevator.set(-1);
	}
	else if(operateStick.getYButton())
	{
		elevator.set(1);
	}
	else
	{
		elevator.set(0);
	}
	
	
	
	//Intake
	if(operateStick.getBumper(Hand.kRight))
	{
		intake.set(-1);
		intakeLeft.set(1);
	}

	else if(operateStick.getBumper(Hand.kLeft))
	{
		intake.set(1);
		intakeLeft.set(-1);
	
	}
	else{
		intake.set(0);
		intakeLeft.set(0);
	}
	
	
		driveTrain.arcadeDrive(-driveStick.getRawAxis(1), driveStick.getRawAxis(4));
		
	
  }

}
