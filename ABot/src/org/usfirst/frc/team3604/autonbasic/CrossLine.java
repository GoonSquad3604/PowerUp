package org.usfirst.frc.team3604.autonbasic;

import org.usfirst.frc.team3604.robot.*;

public class CrossLine {

	public static void run() {
		if(Auton.getStage() == -1) {
			Auton.resetThings();
		}
		else if(Auton.getStage() == 0 && Robot.driveAutonSimple(125, 125)) {
			Auton.resetThings();
		}
	}
}
