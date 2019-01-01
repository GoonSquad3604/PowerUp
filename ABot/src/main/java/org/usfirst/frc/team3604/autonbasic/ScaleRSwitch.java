package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleRSwitch {

	public static void run() {
		
		if(Auton.getStage() == -1) {
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
		else if(Auton.getStage() == 3 && Robot.shootCube(0.8)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.3) && Robot.turnAuton(-167, -152, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.limelightPickUp()) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.raiseArm(Robot.SWITCHHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && Robot.shortDrive(5)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.shootCube(0.8)) {
			Auton.resetThings();
		}
		
	}
	
	
}
