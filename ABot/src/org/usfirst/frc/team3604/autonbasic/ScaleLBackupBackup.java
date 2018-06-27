package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleLBackupBackup {

	public static void run() {
	
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(200, 200)) {
			Auton.resetThings();
		}
//		else if(Auton.getStage() == 1 && Robot.raiseArm(12000, true)) {
//			Auton.resetThings();
//		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(84, 42)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.shootCube(0.7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.5) && Robot.driveAutonSimple(-79, -37)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.SWITCHHEIGHT, true)) {
			Auton.resetThings();
		}
		
	}
}
