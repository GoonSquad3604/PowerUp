package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_SwitchL extends Auton{

	@Override
	public void run() {
		if(Robot.switchValue == 'L') {
			SwitchL.run();
		}
		else {
			CrossLine.run();
		}
	}

}
