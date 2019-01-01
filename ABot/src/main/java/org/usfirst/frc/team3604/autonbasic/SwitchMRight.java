package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class SwitchMRight {

	public static void run() {
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.driveAutonSimple(70, 25)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(Robot.SWITCHHEIGHT, false) && Robot.driveAutonSimple(20, 70)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SWITCHHEIGHT, false) && Robot.shortDriveSlow(13)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.shootCube(0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.delayTime(0.5)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.shortDriveSlow(-20)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.lowerArm(-11500, false) && Robot.turnAutonSlow(-22, -19, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.lowerArm(-11500, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && (Robot.drivePickUpSpin() || Robot.shortDriveSlow(16))) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.drivePickUpSpin()) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 10 && Robot.shortDriveSlow(-8)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 11 && Robot.raiseArm(Robot.SWITCHHEIGHT, false) && Robot.turnAutonSlow(-6, 0, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 12 && Robot.shortDriveSlow(22)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 13 && Robot.shootCube(0.6)) {
			Robot.testTime.stop();
			Auton.resetThings();
		}
		else if(Auton.getStage() == 14 && Robot.lowerArm(-5000, false, 0.4) && Robot.shortDriveSlow(-6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 15 && Robot.lowerArm(-5000, true)) {
			Auton.resetThings();
		}
		
	}
}
