package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class ScaleL {

	public static void run() {
	
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
		
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.raiseArmSlow(6500, false) && Robot.driveAutonSimple(200, 200)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 1 && Robot.raiseArm(12000, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 2 && Robot.raiseArm(Robot.SCALEHEIGHT, false) && Robot.driveAutonSimple(82, 40)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 3 && Robot.raiseArm(Robot.SCALEHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 4 && Robot.shootCube(0.7)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 5 && Robot.lowerArm(Robot.BOTTOMHEIGHT, false, 0.3) && Robot.turnAuton(135, 141, 0.6)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 6 && Robot.lowerArm(Robot.BOTTOMHEIGHT, true)) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 7 && Robot.shortDrive(3)) {
			Auton.resetThings();
		}
		
	}
}
