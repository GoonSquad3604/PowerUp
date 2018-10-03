package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScaleOnlyR extends Auton{

	@Override
	public void run() {
		if(Robot.scaleValue == 'R') {
			ScaleRightBang.run();
		}
		else if(Robot.scaleValue == 'L') {
			ScaleRAcrossBang.run();
		}	
	}

}
