package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleR {

	public static void run() {
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(5000, false) && Robot.driveAutonSimple(205, 205)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(38, 63)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.shootCube(0.4)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.shortDriveSlow(-7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.SWITCHHEIGHT, true)) {
			Auton.getStage();
		}
		
	}
	
	
}
