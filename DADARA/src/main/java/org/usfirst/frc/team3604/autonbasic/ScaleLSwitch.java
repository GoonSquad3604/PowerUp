package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleLSwitch {

	public static void run() {
	
		if(Auton.getStage() == -1) {
			if(Robot.isRed && Robot.switchValue == 'L') {
				Robot.table.getEntry("pipeline").setNumber(0);
			}
			else if(Robot.isRed && Robot.switchValue == 'R') {
				Robot.table.getEntry("pipeline").setNumber(1);
			}
			else if(!Robot.isRed && Robot.switchValue == 'L'){
				Robot.table.getEntry("pipeline").setNumber(1);
			}
			else if(!Robot.isRed && Robot.switchValue == 'R') {
				Robot.table.getEntry("pipeline").setNumber(0);
			}
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(200, 200)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(84, 42)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.shootCube(0.8)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.1) && Robot.turnAuton(135, 141, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.shortDrive(3)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.limelightPickUp()) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 8 && Robot.raiseArm(Robot.SWITCHHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 9 && Robot.shortDrive(5)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 10 && Robot.shootCube(0.8)) {
			Auton.resetThings();
		}
	}
}
