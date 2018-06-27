package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_SwitchPriorityAcrossR extends Auton{

	@Override
	public void run() {
		if(Robot.switchValue == 'R') {
			SwitchR.run();
		}
		else if(Robot.scaleValue == 'R') {
			ScaleRDouble.run();
		}
		else {
			ScaleRAcross.run();
		}
	}
}
