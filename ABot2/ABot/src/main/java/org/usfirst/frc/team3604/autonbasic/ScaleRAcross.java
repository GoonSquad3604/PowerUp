package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleRAcross {

	public static void run() {
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.driveAutonSimple(172, 172)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.driveAutonSimple(25, 78)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArmSlow(5000, false) && Robot.driveAutonSimple(132, 132)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(85, 25)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.shortDriveSlow(4)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.shootCube(0.4)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDriveSlow(-7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.turnAuton(-183, -177, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 10 && Robot.limelightPickUp()) {
			Auton.resetThings();
		}
		
	
	
	
	}
}
