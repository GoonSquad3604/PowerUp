/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.*;

public class Robot extends TimedRobot {
	
	//Drivetrain
	public static WPI_TalonSRX leftMain;
	public static WPI_TalonSRX rightMain;
	public static WPI_TalonSRX leftSlave;
	public static WPI_TalonSRX rightSlave;
	public static DifferentialDrive driveTrain;
	
	//Elevator/Manipulator
	public static WPI_TalonSRX elevator;
	public static WPI_TalonSRX intakeLeft;
	public static WPI_TalonSRX intake;
	
	//Climber
	public static WPI_TalonSRX climber;
	
	//Xbox Controller
	private static XboxController driveStick;
	private static XboxController operateStick;
	static double axis1;
	static double axis4;
	
	@Override
	public void robotInit() {
		
		
		//Drivetrain
		leftMain = new WPI_TalonSRX(15);
		rightMain = new WPI_TalonSRX(1);
		leftSlave = new WPI_TalonSRX(14);
		rightSlave = new WPI_TalonSRX(2);
		leftSlave.follow(leftMain);
		rightSlave.follow(rightMain);
		
		
		elevator = new WPI_TalonSRX(13);
		elevator.setInverted(true);
		intakeLeft = new WPI_TalonSRX(3);
		intake = new WPI_TalonSRX(4);
		intakeLeft.setInverted(true);
		intake.setInverted(true);
		
		//Climber
		climber = new WPI_TalonSRX(0);
		
		//XboxControllers
		driveStick = new XboxController(0);
		operateStick = new XboxController(1);
  
    driveTrain = new DifferentialDrive(leftMain,rightMain);
    
    elevator.setSelectedSensorPosition(0, 0, 0);
		
	}

	@Override
	public void teleopPeriodic() {
		
		//Dead Zone For Drivetrain Controller
		if(Math.abs(driveStick.getRawAxis(1)) < .1) {
			axis1 = 0.0;
		}
		else {
			axis1 = driveStick.getRawAxis(1);
		}
				
		if(Math.abs(driveStick.getRawAxis(4)) < .1) {
			axis4 = 0.0;
		}
		else {
			axis4 = driveStick.getRawAxis(4);
		}
				
		
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
		
		//Climber
		if(operateStick.getBackButton())
		{
			climber.set(1);
		}
		else
		{
			climber.set(0);
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
		else if(!operateStick.getAButton()){
			intake.set(0);
			intakeLeft.set(0);
		}
		
		driveTrain.arcadeDrive(-axis1*(0.93), axis4*(0.93));
		
		
		
	}
	
}