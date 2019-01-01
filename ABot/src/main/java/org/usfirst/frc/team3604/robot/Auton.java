package org.usfirst.frc.team3604.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public abstract class Auton {
	
	static public int stage = -1;
	
	static public void setStage(int newStage) {
		stage = newStage;
	}
	
	static public int getStage() {
		return stage;
	}
	
	
	
	public abstract void run();
	
	static public void resetThings() {
		Robot.time.reset();
		Robot.armTime.reset();
		Robot.turnTime.reset();
		Robot.pickUpTime.reset();
		Robot.delayTime.reset();
		Robot.autonTime.reset();
		Robot.intake.set(ControlMode.PercentOutput, 0);
		Robot.intakeLeft.set(ControlMode.PercentOutput, 0);
		Robot.stop = false;
		//Robot.leftMain.set(ControlMode.PercentOutput, 0);
		//Robot.rightMain.set(ControlMode.PercentOutput, 0);
		Robot.elevator.set(ControlMode.PercentOutput, 0);
		Robot.leftMain.setSelectedSensorPosition(0, 0, 0);
		Robot.rightMain.setSelectedSensorPosition(0, 0, 0);
		Robot.leftMove = false;
		Robot.rightMove = false;
		Robot.encR = 0;
		Robot.encL = 0;
		Robot.ocelate = false;
		Robot.motorZero = false;
		Robot.hitDis = false;
		Robot.shortDriveReset = false;
		stage++;
		
	}
	
}
