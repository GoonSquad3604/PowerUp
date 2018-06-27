package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleLDouble {

	public static void run() {
	
//		if(Robot.isRed && Robot.switchValue == 'L') {
//			Robot.table.getEntry("pipeline").setNumber(0);
//		}
//		else if(Robot.isRed && Robot.switchValue == 'R') {
//			Robot.table.getEntry("pipeline").setNumber(1);
//		}
//		else if(!Robot.isRed && Robot.switchValue == 'L'){
//			Robot.table.getEntry("pipeline").setNumber(1);
//		}
//		else if(!Robot.isRed && Robot.switchValue == 'R') {
//			Robot.table.getEntry("pipeline").setNumber(0);
//		}
		
		if(Auton.getStage() == -1) {
			
			
//			//DELETE FOR WORLDS\/\/\/
//			Robot.table.getEntry("pipeline").setNumber(0);
//			//DELETE FOR WORLDS/\/\/\
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(208, 208)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(12000, true)) {
			Auton.resetThings();
			
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(71, 30)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.shootCube(0.7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.3) && Robot.turnAuton(137, 139, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDrive(4)) {
			Auton.resetThings();
		}
//		else if(Auton.getStage() == 8 && Robot.limelightPickUp()) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 9 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, false) && Robot.driveAutonSimple(-((Math.abs((Robot.visionL)*0.75))), -((Math.abs((Robot.visionR)*0.8))))) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 10 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, false) && Robot.turnAuton(100, 103, 0.6)) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 11 && Robot.raiseArm(Robot.SCALELEVELHEIGHT, true)) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 12 && Robot.shortDriveSlow(2)) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 13 && Robot.shootCube(0.8)) {
//			Robot.testTime.stop();
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 14 && Robot.shortDriveSlow(-4)) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 15 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
//			Auton.resetThings();
//		}
//		//USUALLY NOT REACHED
//		else if(Auton.getStage() == 16 && Robot.turnAuton(132, 137, 0.6)) {
//			Auton.resetThings();
//		}
//		else if(Auton.getStage() == 17 && Robot.limelightPickUp()) {
//			
//			Auton.resetThings();
//		}
	}
}
