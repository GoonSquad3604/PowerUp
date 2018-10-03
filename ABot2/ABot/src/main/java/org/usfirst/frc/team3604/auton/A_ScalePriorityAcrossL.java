package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScalePriorityAcrossL extends Auton{

	@Override
	public void run() {
		
		if(Robot.scaleValue == 'L') {
			ScaleLDouble.run();
		}
		else if(Robot.switchValue == 'L') {
			SwitchL.run();
		}
		else {
			ScaleLAcross.run();
		}
		
	}

}
