package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_SwitchM extends Auton{

	@Override
	public void run() {
		if(Robot.switchValue == 'L') {
			SwitchMLeft.run();
		}
		else if(Robot.switchValue == 'R') {
			SwitchMRight.run();
		}
		
	}

}
