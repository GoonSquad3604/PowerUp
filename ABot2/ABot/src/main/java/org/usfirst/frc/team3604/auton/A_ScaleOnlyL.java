package org.usfirst.frc.team3604.auton;

import org.usfirst.frc.team3604.autonbasic.*;
import org.usfirst.frc.team3604.robot.*;

public class A_ScaleOnlyL extends Auton{

	@Override
	public void run() {
		if(Robot.scaleValue == 'L') {
			ScaleLDouble.run();
		}
		else if(Robot.scaleValue == 'R') {
			ScaleLAcross.run();
		}
	}	
}
