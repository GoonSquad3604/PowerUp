package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleRightBang {

	public static void run() {
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(208, 208)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(12000, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(30, 70)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.shootCube(0.7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.3) && Robot.turnAuton(-139, -137, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDrive(4)) {
			Auton.resetThings();
		}
	}
}
