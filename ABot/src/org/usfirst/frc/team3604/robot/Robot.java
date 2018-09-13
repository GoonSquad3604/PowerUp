/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//ACCEPT THIS RICKY
//Please
//UUHDfiuhsdo

package org.usfirst.frc.team3604.robot;

import org.usfirst.frc.team3604.auton.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.GenericHID.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.*;

public class Robot extends IterativeRobot {
	
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
	public static final double SWITCHHEIGHT = 6500.0;
	public static final double SCALEHEIGHT = 35000.0; //32000
	public static final double SCALELEVELHEIGHT = 29000.0;
	public static final double BOTTOMHEIGHT = -11500.0;
	public static double elevatorPos = 0.0;
	
	//Climber
	public static WPI_TalonSRX climber;
	
	//Xbox Controller
	private static XboxController driveStick;
	private static XboxController operateStick;
	static double axis1;
	static double axis4;

	//Drive Auton
	
	//ABOT RATIO
	static final double PULSEPERINCH = 54.324887242033607942445657897818;
	
	//BBOT RATIO
	//static final double PULSEPERINCH = 50.5;
	
	public static int encR = 0;
	public static int encL = 0;
	public static double visionR = 0.0;
	public static double visionL = 0.0;
	public static boolean leftMove = false;
	public static boolean rightMove = false;
	public static boolean motorZero = false;
	public static boolean hitDis = false;
	public static boolean shortDriveReset = false;
	public static boolean runShortDrive = false;
	
	//Auton
	private static Auton auto;
	public static char switchValue;
	public static char scaleValue;
	private static int auton = 0;
	
	//Turn Auton
	public static boolean ocelate = false;
	public static boolean stop = false;
	
	//Timer
	public static Timer time;
	public static Timer armTime;
	public static Timer turnTime;
	public static Timer pickUpTime;
	public static Timer testTime;
	public static Timer delayTime;
	public static Timer autonTime;
	
	//Arduino
	DigitalInput ardunioZero;
	DigitalInput ardunioOne;
	DigitalInput ardunioTwo;
	DigitalInput ardunioThree;
	
	//Gyro
	double[] ypr = new double[3];
	static double yaw;
	static PigeonIMU gyro;
	
	//Limelight
	public static NetworkTable table;
	static final double CONSTANTCURRENT = 15.0;
	public static double tx;
	public static double ty;
	public static double tv;
	public static boolean targetAcquired = false;
	public static boolean isRed;

	@Override
	public void robotInit() {
		
		SmartDashboard.putBoolean("Override", false);
		SmartDashboard.putNumber("Auton", 0);
		
		//Timers
		time = new Timer();
		armTime = new Timer();
		turnTime = new Timer();
		pickUpTime = new Timer();
		testTime = new Timer();
		delayTime = new Timer();
		autonTime = new Timer();
		
		//Gyro
		gyro = new PigeonIMU(0);
		
		//Drivetrain
		leftMain = new WPI_TalonSRX(15);
		rightMain = new WPI_TalonSRX(1);
		leftSlave = new WPI_TalonSRX(14);
		rightSlave = new WPI_TalonSRX(2);
		leftSlave.follow(leftMain);
		rightSlave.follow(rightMain);
		
		//Elevator/Manipulator
		elevator = new WPI_TalonSRX(13);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
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
	
		//Arduino
		ardunioZero = new DigitalInput(0);
		ardunioOne = new DigitalInput(1);
		ardunioTwo = new DigitalInput(2);
		ardunioThree = new DigitalInput(3);
		
		//Limelight
		table = NetworkTableInstance.getDefault().getTable("limelight");
		
		table.getEntry("stream").setNumber(0);
		table.getEntry("ledMode").setNumber(1);
		table.getEntry("camMode").setNumber(0);
		
	}

	@Override
	public void disabledPeriodic() {
		
		if(ardunioZero.get()) {
			auton+=1;
		}
		if(ardunioOne.get()) {
			auton+=2;
		}
		if(ardunioTwo.get()) {
			auton+=4;
		}
		if(ardunioThree.get()) {
			auton+=8;
		}
		
		if(SmartDashboard.getBoolean("Override", false)) {
			auton = (int) SmartDashboard.getNumber("Auton", 0);
		}
		
		SmartDashboard.putNumber("Auton Number", auton);
		auton = 0;
		
		table.getEntry("stream").setNumber(0);
		table.getEntry("ledMode").setNumber(1);
		table.getEntry("camMode").setNumber(0);
		
		operateStick.setRumble(RumbleType.kLeftRumble, 0);
		operateStick.setRumble(RumbleType.kRightRumble, 0);
		driveStick.setRumble(RumbleType.kLeftRumble, 0);
		driveStick.setRumble(RumbleType.kRightRumble, 0);
		
	}
	
	
	
	@Override
	public void robotPeriodic() {
		
		
		
		
		if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red){
			isRed = true;
		}
		else if(DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Blue) {
			isRed = false;
		}
		
		gyro.getYawPitchRoll(ypr);
		yaw = -ypr[0];
		elevatorPos = -elevator.getSelectedSensorPosition(0);
		//System.out.println(elevatorPos);
		tv = table.getEntry("tv").getDouble(0);
		ty = table.getEntry("ty").getDouble(0);
		tx = table.getEntry("tx").getDouble(0);		
		//SmartDashboard.putNumber("Current", intake.getOutputCurrent());
		System.out.println(testTime.get());
		
		
	}

	@Override
	public void autonomousInit() {
		
		table.getEntry("ledMode").setNumber(1);
		
		auton = 0;
		
		//Arduino
		if(ardunioZero.get()) {
			auton+=1;
		}
		if(ardunioOne.get()) {
			auton+=2;
		}
		if(ardunioTwo.get()) {
			auton+=4;
		}
		if(ardunioThree.get()) {
			auton+=8;
		}
		
		if(SmartDashboard.getBoolean("Override", false)) {
			auton = (int) SmartDashboard.getNumber("Auton", 0);
		}
		
		//Timers
		time.start();
		time.reset();
		armTime.start();
		armTime.reset();
		turnTime.start();
		turnTime.reset();
		pickUpTime.start();
		pickUpTime.reset();
		testTime.start();
		testTime.reset();
		delayTime.start();
		delayTime.reset();
		
		
		//Drive Auton
		leftMove = false;
		rightMove = false;
		encR = 0;
		encL = 0;
		motorZero = false;
		
		//Turn Auton
		ocelate = false;
		gyro.setYaw(0, 0);
		
		//Elevator
		elevator.setSelectedSensorPosition(0, 0, 0);
		
		double fVal = 1.0;
		double pVal = 0.3;
		double iVal = 0.0;
		double dVal = 0.0;
		
		//Motion Magic Stuff Left
		leftMain.configOpenloopRamp(0, 0);
		leftMain.selectProfileSlot(0, 0);
		leftMain.configMotionCruiseVelocity(950, 0);
		leftMain.configMotionAcceleration(950, 0);
		leftMain.selectProfileSlot(0, 0);
		leftMain.config_kF(0, fVal, 0);
		leftMain.config_kP(0, pVal, 0);
		leftMain.config_kI(0, iVal, 0);
		leftMain.config_kD(0, dVal, 0);
		leftMain.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		leftMain.setSensorPhase(false);
		leftMain.setInverted(false);
		leftMain.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
		leftMain.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
		leftMain.configNominalOutputForward(0, 0);
		leftMain.configNominalOutputReverse(0, 0);
		leftMain.configPeakOutputForward(1, 0);
		leftMain.configPeakOutputReverse(-1, 0);
		
		//Motion Magic Stuff Right
		rightMain.configOpenloopRamp(0, 0);
		rightMain.configMotionCruiseVelocity(950, 0);
		rightMain.configMotionAcceleration(950, 0);
		rightMain.selectProfileSlot(0, 0);
		rightMain.config_kF(0, fVal, 0);
		rightMain.config_kP(0, pVal, 0);
		rightMain.config_kI(0, iVal, 0);
		rightMain.config_kD(0, dVal, 0);
		rightMain.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);//
		rightMain.setSensorPhase(false);
		rightMain.setInverted(false);
		rightMain.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
		rightMain.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);
		rightMain.configNominalOutputForward(0, 0);
		rightMain.configNominalOutputReverse(0, 0);
		rightMain.configPeakOutputForward(1, 0);
		rightMain.configPeakOutputReverse(-1, 0);
		
		//Reset Drive Train Encoder
		leftMain.setSelectedSensorPosition(0, 0, 0);
		rightMain.setSelectedSensorPosition(0, 0, 0);
		
		Auton.setStage(-1);
		
		/*
		 * Autons should be mostly done but the case below is not updated
		*/
		
		//Set Auton
		switch(auton) {
			case 0: auto = new A_SwitchM();
				break;
			case 1: auto = new A_ScaleOnlyRDouble();
				break;
			case 2: auto = new A_ScaleOnlyLDouble();
				break;
			case 3: auto = new A_ScaleSwitchR();
				break;
			case 4: auto = new A_ScaleSwitchL();
				break;
			case 5: auto = new A_SwitchPriorityAcrossR();
				break;
			case 6: auto = new A_SwitchPriorityAcrossL();
				break;
			case 7:  auto = new A_Nothing();
				break;
			case 8: auto = new A_ScalePriorityAcrossR();
				break;
			case 9: auto = new A_ScalePriorityAcrossL();
				break;
			case 10: auto = new A_SwitchPriorityStayR();
				break;
			case 11: auto = new A_SwitchPriorityStayL();
				break;
			case 12: auto = new A_ScalePriorityStayR();
				break;
			case 13: auto = new A_MoveGoonGetOutTheWo();
				break;
			case 14: auto = new A_ScaleOnlyL();
				break;
			case 15: auto = new A_ScaleOnlyR();
				break;
			default: auto = new A_Nothing();
				break;
		}
		
	}

	@Override
	public void autonomousPeriodic() {
		
		//Run Auton
		if(!DriverStation.getInstance().getGameSpecificMessage().isEmpty()) {
			switchValue = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
			scaleValue = DriverStation.getInstance().getGameSpecificMessage().charAt(1);
			auto.run();
		}
	
		//Use to tune PID
//		double targetDis = 0;
//		targetDis *= PULSEPERINCH;
//		leftMain.set(ControlMode.MotionMagic, targetDis);
//		rightMain.set(ControlMode.MotionMagic, -targetDis);
//		System.out.println(leftMain.getSelectedSensorPosition(0)/PULSEPERINCH);
	}

	@Override
	public void teleopInit() {
	
		//Initalize Drive Train
		driveTrain = null;
		driveTrain = new DifferentialDrive(leftMain,rightMain);
		
		//Limelight
		table.getEntry("camMode").setNumber(1);
		
		elevator.setSelectedSensorPosition(0, 0, 0);
		
		
	}
	
	@Override
	public void teleopPeriodic() {
		
		
//		operateStick.setRumble(RumbleType.kLeftRumble, Math.random());
//		operateStick.setRumble(RumbleType.kRightRumble, Math.random());
		
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
		else if(operateStick.getBButton()) {
			intake.set(0.4);
			intakeLeft.set(-0.4);
		}
		else if(operateStick.getStartButton()) {
			intake.set(0.75);
			intakeLeft.set(-0.75);
		}
		else if(!operateStick.getAButton()){
			intake.set(0);
			intakeLeft.set(0);
		}
		
		//Shoot and Drive
		if(operateStick.getAButton()) {
			intake.set(0.35);
			intakeLeft.set(-0.35);
			driveTrain.arcadeDrive(-0.5, 0);
			driveStick.setRumble(RumbleType.kLeftRumble, 0.5);
			driveStick.setRumble(RumbleType.kRightRumble, 0.5);
		}
		else if(driveStick.getBumper(Hand.kRight)) {
			driveTrain.arcadeDrive(-0.5, 0);
			operateStick.setRumble(RumbleType.kLeftRumble, 0.5);
			operateStick.setRumble(RumbleType.kRightRumble, 0.5);
		}
		else {
			driveTrain.arcadeDrive(-axis1*(0.93), axis4*(0.93));
			driveStick.setRumble(RumbleType.kLeftRumble, 0);
			driveStick.setRumble(RumbleType.kRightRumble, 0);
			operateStick.setRumble(RumbleType.kLeftRumble, 0);
			operateStick.setRumble(RumbleType.kRightRumble, 0);
		}
		
		
	}

	public static boolean driveAuton(double targetDis) {
		
		
		boolean finished;
		double distance;
		double left;
		double right;
		
		
		targetDis *= PULSEPERINCH;
		
		left = (Math.abs(leftMain.getSelectedSensorPosition(0)));
		right = (Math.abs(rightMain.getSelectedSensorPosition(0)));
		
		distance = (left + right)/2.0;	
		
		
		if((Math.abs(leftMain.getSelectedSensorPosition(0)) > 5 || 
				Math.abs(rightMain.getSelectedSensorPosition(0)) > 5) && !motorZero) {
			leftMain.setSelectedSensorPosition(0, 0, 0);
			rightMain.setSelectedSensorPosition(0, 0, 0);
			leftMove = false;
			rightMove = false;
			encR = 0;
			encL = 0;
			motorZero = false;
			finished = false;
		}
		else {
			motorZero  = true;
		
			if(Math.abs(distance) < 512) {
			
				if(targetDis < 0) {
					leftMain.set(ControlMode.PercentOutput, -1);
					rightMain.set(ControlMode.PercentOutput, 1);
				}
				else {
					leftMain.set(ControlMode.PercentOutput, 1);
					rightMain.set(ControlMode.PercentOutput, -1);
				}
				finished = false;
			}
			else if(Math.abs(distance) < Math.abs(targetDis)) {
				time.reset();
				leftMain.set(ControlMode.MotionMagic, targetDis);
				rightMain.set(ControlMode.MotionMagic, -targetDis);
				finished = false;
			}
		
			else {
				leftMain.set(ControlMode.PercentOutput, 0);
				rightMain.set(ControlMode.PercentOutput, 0);
				finished = true;
			}
		
			if(Math.abs(leftMain.getSelectedSensorPosition(0)) > 5)	{
				leftMove = true;
			}
			if(Math.abs(rightMain.getSelectedSensorPosition(0)) > 5)	{
				rightMove = true;
			}
			
			if(Math.abs(leftMain.getSelectedSensorPosition(0)) > 10  && leftMove){
				encL = leftMain.getSelectedSensorPosition(0);
			}
			else {
				if(Math.abs(rightMain.getSelectedSensorPosition(0)) > 10) {
					leftMain.setSelectedSensorPosition(-rightMain.getSelectedSensorPosition(0), 0 ,0);
				}
				else {
					leftMain.setSelectedSensorPosition(encL, 0, 0);
				}
			}
			if(Math.abs(rightMain.getSelectedSensorPosition(0)) > 10 && rightMove){
				encR = rightMain.getSelectedSensorPosition(0);
			}
			else{
				if(Math.abs(leftMain.getSelectedSensorPosition(0)) > 10) {
					rightMain.setSelectedSensorPosition(-leftMain.getSelectedSensorPosition(0), 0 ,0);
				}
				else {
					rightMain.setSelectedSensorPosition(encR, 0, 0);
				}
			}
		}
		
		return finished;
	}
	
	public static boolean shortDrive(int targetDis) {
		
		boolean finished;
		targetDis *= PULSEPERINCH;
		
		double left;
		double right;
		double distance;
		
		if(!shortDriveReset && (Math.abs(leftMain.getSelectedSensorPosition(0)) > 5 || 
				Math.abs(rightMain.getSelectedSensorPosition(0)) > 5)) {
			leftMain.setSelectedSensorPosition(0, 0, 0);
			rightMain.setSelectedSensorPosition(0, 0, 0);
			finished = false;
			
		}
		else
		{
			shortDriveReset = true;
			left = (Math.abs(leftMain.getSelectedSensorPosition(0)));
			right = (Math.abs(rightMain.getSelectedSensorPosition(0)));
		
			distance = (left + right)/2.0;	
		
			if(Math.abs(distance) < Math.abs(targetDis)) {
				if(targetDis < 0) {
					leftMain.set(ControlMode.PercentOutput, -1);
					rightMain.set(ControlMode.PercentOutput, 1);
				}
				else if(targetDis > 0){
					leftMain.set(ControlMode.PercentOutput, 1);
					rightMain.set(ControlMode.PercentOutput, -1);
				}
				finished = false;
			}
		else {
				leftMain.set(ControlMode.PercentOutput, 0);
				rightMain.set(ControlMode.PercentOutput, 0);	
				finished = true;
			}
		}
		
		return finished;
		
	}
	
	public static boolean shortDriveTime(double time) {
		
		boolean finish;
		
		if(autonTime.get() < time) {
			leftMain.set(ControlMode.PercentOutput, 0.8);
			rightMain.set(ControlMode.PercentOutput, -0.8);
			finish = false;
		}
		else {
			leftMain.set(ControlMode.PercentOutput, 0);
			rightMain.set(ControlMode.PercentOutput, 0);	
			finish = true;
		}
		
		return finish;
		
	}
	
	public static boolean shortDriveSlow(int targetDis) {
		
		boolean finished;
		targetDis *= PULSEPERINCH;
		
		double left;
		double right;
		double distance;
		
		if(!shortDriveReset && (Math.abs(leftMain.getSelectedSensorPosition(0)) > 5 || 
				Math.abs(rightMain.getSelectedSensorPosition(0)) > 5)) {
			leftMain.setSelectedSensorPosition(0, 0, 0);
			rightMain.setSelectedSensorPosition(0, 0, 0);
			finished = false;
			
		}
		else {
			shortDriveReset = true;
			
			left = (Math.abs(leftMain.getSelectedSensorPosition(0)));
			right = (Math.abs(rightMain.getSelectedSensorPosition(0)));
		
			distance = (left + right)/2.0;	
			
			if(Math.abs(distance) < Math.abs(targetDis)) {
				if(targetDis < 0) {
					leftMain.set(ControlMode.PercentOutput, -0.5);
					rightMain.set(ControlMode.PercentOutput, 0.5);
				}
				else if(targetDis > 0){
					leftMain.set(ControlMode.PercentOutput, 0.5);
					rightMain.set(ControlMode.PercentOutput, -0.5);
				}
				finished = false;
			}
			else{
				leftMain.set(ControlMode.PercentOutput, 0);
				rightMain.set(ControlMode.PercentOutput, 0);
				finished = true;
			}
		}
		return finished;
	}

	public static boolean turnAuton(double min, double max, double delay) {
		
		boolean finish;
		
		if(time.get() < delay && !stop) {
			leftMain.set(ControlMode.PercentOutput, 0);
			rightMain.set(ControlMode.PercentOutput, 0);
			turnTime.reset();
			finish = false;
		}
		else if(yaw < min && !stop)
        {
            leftMain.set(ControlMode.PercentOutput, 0.4);
            rightMain.set(ControlMode.PercentOutput, 0.4);
            turnTime.reset();
            finish = false;
        }
        else if(yaw > max && !stop)
        {
        	leftMain.set(ControlMode.PercentOutput, -0.4);
            rightMain.set(ControlMode.PercentOutput, -0.4);
            turnTime.reset();
            finish = false;   
        }
        else
        {
        	stop = true;
        	leftMain.set(ControlMode.PercentOutput, 0);
            rightMain.set(ControlMode.PercentOutput, 0);
        	
           
        	if(turnTime.get() > 0.1) {
        		stop = false;
        		//gyro.setYaw(0, 0);
        		finish = true; 
        	}
        	else {
        		finish = false;
        	}
        	  
        }
		
		leftMain.setSelectedSensorPosition(0, 0, 0);
		rightMain.setSelectedSensorPosition(0, 0, 0);
		
		return finish;
		
	}
	
	public static boolean turnAutonSlow(double min, double max, double delay) {
		
		boolean finish;
		
		if(time.get() < delay && !stop) {
			leftMain.set(ControlMode.PercentOutput, 0);
			rightMain.set(ControlMode.PercentOutput, 0);
			turnTime.reset();
			finish = false;
		}
		else if(yaw < min && !stop)
        {
            leftMain.set(ControlMode.PercentOutput, 0.3);
            rightMain.set(ControlMode.PercentOutput, 0.3);
            turnTime.reset();
            finish = false;
        }
        else if(yaw > max && !stop)
        {
        	leftMain.set(ControlMode.PercentOutput, -0.3);
            rightMain.set(ControlMode.PercentOutput, -0.3);
            turnTime.reset();
            finish = false;   
        }
        else
        {
        	stop = true;
        	leftMain.set(ControlMode.PercentOutput, 0);
            rightMain.set(ControlMode.PercentOutput, 0);
        	
           
        	if(turnTime.get() > 0.1) {
        		stop = false;
        		//gyro.setYaw(0, 0);
        		finish = true; 
        	}
        	else {
        		finish = false;
        	}
        	  
        }
		
		leftMain.setSelectedSensorPosition(0, 0, 0);
		rightMain.setSelectedSensorPosition(0, 0, 0);
		
		return finish;
		
	}
	
	public static boolean moveArm(double height, boolean ownStep) {
		
		boolean finish;
		
		final double RANGE = 500.0;
		System.out.println(RANGE);
		
		if(elevatorPos < height - RANGE) {
			elevator.set(1);
			
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else if(elevatorPos > height + RANGE) {
			elevator.set(-1);
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else {
			elevator.set(0);
			finish = true;
		}
		return finish;
		
	}
	
	public static boolean raiseArm(double height, boolean ownStep) {
		
		boolean finish;
		
		
		if(elevatorPos < height) {
			elevator.set(1);
			
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else {
			elevator.set(0);
			finish = true;
		}
		return finish;
		
	}
	
	public static boolean raiseArmSlow(double height, boolean ownStep) {
		
		boolean finish;
		
		
		if(elevatorPos < height) {
			elevator.set(0.5);
			
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else {
			elevator.set(0);
			finish = true;
		}
		return finish;
		
	}
	
	public static boolean lowerArm(double height, boolean ownStep) {
		
		boolean finish;
		
		
		if(elevatorPos > height) {
			elevator.set(-1);
			
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else {
			elevator.set(0);
			finish = true;
		}
		return finish;
		
	}
	
	public static boolean lowerArm(double height, boolean ownStep, double delay) {
		
		boolean finish;
		
		
		if(time.get() > delay && elevatorPos > height) {
			elevator.set(-1);
			
			if(ownStep) {
				finish = false;
			}
			else {
				finish = true;
			}
		}
		else {
			elevator.set(0);
			finish = true;
		}
		return finish;
		
	}
	
	public static boolean shootCube(double power) {
		
		boolean finish;
		double timeShoot = 0.4;
		
		
		
		if(time.get() < timeShoot) {
			intake.set(power);
			intakeLeft.set(-power);
			finish = false;
		}
		else {
			intake.set(0);
			intakeLeft.set(0);
			finish = true;
		}
		
		leftMain.setSelectedSensorPosition(0, 0, 0);
		rightMain.setSelectedSensorPosition(0, 0, 0);
		
		return finish;
	}
	
	public static boolean suckCube() {
		
		intake.set(-1);
		intakeLeft.set(1);
		
		return true;
		
	}
	
	public static boolean initalRaise() {
		
		boolean finish;
		
		if(elevatorPos < 2000) {
			elevator.set(1);	
			finish = false;
		}
		else {
			elevator.set(0);
			finish = true;
		}
		
		return finish;
	}
	
	public static boolean initalRaiseAlwaysTrue() {
		
		boolean finish;
		
		if(elevatorPos < 2000) {
			elevator.set(1);	
			finish = false;
		}
		else {
			elevator.set(0);
			finish = true;
		}
		
		return finish;
	}
	
	static boolean test = false;
	
	public static boolean limelightPickUp(){
		
		boolean finish;
		boolean target;
		
		if(tv == 1.0) {
			test = true;
		}
		
		System.out.println(test);
		
		if(intake.getOutputCurrent() >= CONSTANTCURRENT || intakeLeft.getOutputCurrent() >= CONSTANTCURRENT) {
			if(pickUpTime.get() > 0.3) {
				finish = true;
			}
			else {
				finish = false;
			}
			
		}
		else {
			pickUpTime.reset();
			finish = false;
		}
		
		if(!finish) {
			
			target = (tv == 0.0 || ty <= -15);
			
			if(target) {
				leftMain.set(ControlMode.PercentOutput, 0);
		        rightMain.set(ControlMode.PercentOutput, 0);
			}
			else {
			
				if(tx < -4) {
					leftMain.set(ControlMode.PercentOutput, 0.2);
					rightMain.set(ControlMode.PercentOutput, -0.45);
				}
				else if(tx > 4) {
					leftMain.set(ControlMode.PercentOutput, 0.45);
					rightMain.set(ControlMode.PercentOutput, -0.2);
				}
				else {
					leftMain.set(ControlMode.PercentOutput, 0.4);
					rightMain.set(ControlMode.PercentOutput, -0.4);
				}
			}
		}
		else {
			leftMain.set(ControlMode.PercentOutput, 0);
			rightMain.set(ControlMode.PercentOutput, 0);
		}
		
		if(Math.abs(leftMain.getSelectedSensorPosition(0))/PULSEPERINCH > 85 || Math.abs(rightMain.getSelectedSensorPosition(0))/PULSEPERINCH > 85) {
			runShortDrive = true;
		}
		
		
		
		visionL = leftMain.getSelectedSensorPosition(0)/PULSEPERINCH;
		visionR = rightMain.getSelectedSensorPosition(0)/PULSEPERINCH;
		
		if(finish) {
			intake.set(0);
			intakeLeft.set(0);
		}
		else {
			intake.set(-1);
			intakeLeft.set(1);
		}
		
		return finish;
	
	}

	public static boolean pickUp = false;
	
	public static boolean suckCubeCurrent(){
		
		boolean finish = true;
		
		if(!pickUp && intake.getOutputCurrent() >= CONSTANTCURRENT || intakeLeft.getOutputCurrent() >= CONSTANTCURRENT) {
			if(pickUpTime.get() > 0.25) {
				pickUp = true;
				intake.set(0);
				intakeLeft.set(0);
			}
			else {
				intake.set(-1);
				intakeLeft.set(1);
			}
		}
		else {
			intake.set(-1);
			intakeLeft.set(1);
			pickUpTime.reset();
		}
		
		
		
		
		return finish;
	
	}
	
	
	public static boolean driveAutonSimple(double targetDisLeft, double targetDisRight) {

		boolean finished;
		double distanceLeft;
		double distanceRight;
		
		
		targetDisLeft *= PULSEPERINCH;
		targetDisRight *= PULSEPERINCH;
		
		distanceLeft = Math.abs(leftMain.getSelectedSensorPosition(0));
		distanceRight = Math.abs(rightMain.getSelectedSensorPosition(0));
		
		
		
		if(!motorZero && (Math.abs(leftMain.getSelectedSensorPosition(0)) > 5 || 
				Math.abs(rightMain.getSelectedSensorPosition(0)) > 5)) {
			leftMain.setSelectedSensorPosition(0, 0, 0);
			rightMain.setSelectedSensorPosition(0, 0, 0);
			leftMove = false;
			rightMove = false;
			encR = 0;
			encL = 0;
			finished = false;
		}
		else {
			
			motorZero = true;
		
			
			if(!hitDis && Math.abs(distanceRight) < Math.abs(targetDisRight) && Math.abs(distanceLeft) < Math.abs(targetDisLeft)) {
				time.reset();
				leftMain.set(ControlMode.MotionMagic, targetDisLeft);
				rightMain.set(ControlMode.MotionMagic, -targetDisRight);
				finished = false;
			}
		
			else {
				hitDis = true;
				if(leftMain.getSelectedSensorVelocity(0) <= 0 && rightMain.getSelectedSensorVelocity(0) >= 0) {
					
					leftMain.set(ControlMode.PercentOutput, 0);
					rightMain.set(ControlMode.PercentOutput, 0);
					finished = true;
					
				}
				else {
					leftMain.set(ControlMode.MotionMagic, targetDisLeft);
					rightMain.set(ControlMode.MotionMagic, -targetDisRight);
					finished = false;
				}
			}
		
		}
		
		return finished;
	}
	
	public static boolean drivePickUpSpin() {
		
		boolean finish;
		
		if(intake.getOutputCurrent() >= CONSTANTCURRENT || intakeLeft.getOutputCurrent() >= CONSTANTCURRENT) {
			if(pickUpTime.get() > 0.3) {
				intake.set(0);
				intakeLeft.set(0);
				finish = true;
			}
			else {
				intake.set(-1);
				intakeLeft.set(1);
				finish = false;
			}
			
		}
		else {
			intake.set(-1);
			intakeLeft.set(1);
			pickUpTime.reset();
			finish = false;
		}
		
		return finish;
		
	}
	
	public static boolean delayTime(double time) {
		
		boolean finish;
		
		if(delayTime.get() > time) {
			finish = true;
		}
		else {
			finish = false;
		}
		
		return finish;
		
	}
	
	
		
		
		
	
	
	
	
}