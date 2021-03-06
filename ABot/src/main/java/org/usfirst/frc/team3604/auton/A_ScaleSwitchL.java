package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScaleSwitchL extends Auton{

	@Override
	public void run() {
		if(Robot.switchValue == 'L' && Robot.scaleValue == 'L') {
			ScaleLSwitch.run();
		}
		else if(Robot.switchValue == 'R' && Robot.scaleValue == 'L') {
			ScaleLDouble.run();
		}
		else if(Robot.switchValue == 'L' && Robot.scaleValue == 'R') {
			SwitchL.run();
		}
		else if(Robot.switchValue == 'R' && Robot.scaleValue == 'R')
		{
			ScaleLAcrossSwitch.run();
		}
	}	
}
