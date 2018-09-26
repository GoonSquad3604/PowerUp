package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScaleSwitchR extends Auton{

	@Override
	public void run() {
		if(Robot.switchValue == 'L' && Robot.scaleValue == 'L') {
			ScaleLAcrossSwitch.run();
		}
		else if(Robot.switchValue == 'R' && Robot.scaleValue == 'L') {
			SwitchR.run();
		}
		else if(Robot.switchValue == 'L' && Robot.scaleValue == 'R') {
			ScaleRDouble.run();
		}
		else if(Robot.switchValue == 'R' && Robot.scaleValue == 'R')
		{
			ScaleRSwitch.run();
		}
	}	
}
