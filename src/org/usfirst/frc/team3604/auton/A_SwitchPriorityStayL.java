package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_SwitchPriorityStayL extends Auton{

	@Override
	public void run() {
		
		if(Robot.switchValue == 'L') {
			SwitchL.run();
		}
		else if(Robot.scaleValue == 'L') {
			ScaleL.run();
		}
		else {
			CrossLine.run();
		}
		
	}

}
