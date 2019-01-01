package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleRAcrossBang {

	public static void run() {
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.driveAutonSimple(161, 161)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.driveAutonSimple(35, 85)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArmSlow(5000, false) && Robot.driveAutonSimple(124, 124)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.raiseArm(12000, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(78, 22)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.raiseArm(Robot.SCALEHEIGHT, false)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDriveSlow(7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && Robot.shootCube(0.4)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.shortDriveSlow(-7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 10 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 11 && Robot.turnAuton(177, 183, 0.6)) {
			Auton.resetThings();
		}
		
	
	
	
	}
}
