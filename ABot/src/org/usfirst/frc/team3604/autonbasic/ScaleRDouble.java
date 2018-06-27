package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleRDouble {

	public static void run() {
		
		if(Robot.isRed && Robot.switchValue == 'R') {
			Robot.table.getEntry("pipeline").setNumber(2);
		}
		else if(Robot.isRed && Robot.switchValue == 'L') {
			Robot.table.getEntry("pipeline").setNumber(3);
		}
		else if(!Robot.isRed && Robot.switchValue == 'R'){
			Robot.table.getEntry("pipeline").setNumber(3);
		}
		else if(!Robot.isRed && Robot.switchValue == 'L') {
			Robot.table.getEntry("pipeline").setNumber(2);
		}
		
		if(Auton.getStage() == -1) {
			
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(205, 205)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(38, 63)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.shootCube(0.8)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.shortDriveSlow(-7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.3) && Robot.turnAuton(-167, -152, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDrive(3)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && Robot.limelightPickUp()) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, false) && Robot.driveAutonSimple(-((Math.abs(Robot.visionL)*0.8)), -((Math.abs(Robot.visionR)*0.7)))) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 10 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, false) && Robot.turnAuton(-73, -67, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 11 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 12 && Robot.shootCube(0.8)) {
			Robot.testTime.stop();
			Auton.resetThings();
		}
		else if(Auton.getStage() == 13 && Robot.shortDriveSlow(-4)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 14 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 15 && Robot.turnAuton(-137, -132, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 16 && Robot.limelightPickUp()) {
			Auton.resetThings();
		}
		
	}
	
	
}
