package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScalePriorityStayL extends Auton{

	@Override
	public void run() {
		
		if(Robot.scaleValue == 'L') {
			ScaleLDouble.run();
		}
		else if(Robot.switchValue == 'L') {
			SwitchL.run();
		}
		else {
			CrossLine.run();
		}
		
	}
}