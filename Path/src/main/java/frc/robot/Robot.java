/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Robot extends TimedRobot {
    
    //Drivetrain
    public static WPI_TalonSRX leftMain;
    public static WPI_TalonSRX rightMain;
    public static WPI_TalonSRX leftSlave;
    public static WPI_TalonSRX rightSlave;
    
    @Override
    public void robotInit() {

        //Drivetrain
        leftMain = new WPI_TalonSRX(15);
        rightMain = new WPI_TalonSRX(1);
        leftSlave = new WPI_TalonSRX(14);
        rightSlave = new WPI_TalonSRX(2);
        leftSlave.follow(leftMain);
        rightSlave.follow(rightMain);
        
        leftMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
        rightMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);

        leftMain.config_kP(0, Constants.kP, Constants.kTimeoutMs);
        leftMain.config_kI(0, Constants.kI, Constants.kTimeoutMs);
        leftMain.config_kD(0, Constants.kD, Constants.kTimeoutMs);
        leftMain.config_kF(0, Constants.kF, Constants.kTimeoutMs);

        


        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.kDt, Constants.kVelocity, Constants.kAcceleration, Constants.kJerk);
        Waypoint[] points = new Waypoint[] {
                new Waypoint(-4, -1, Pathfinder.d2r(-45)),
                new Waypoint(-2, -2, 0),
                new Waypoint(0, 0, 0)
        };

        Trajectory trajectory = Pathfinder.generate(points, config);

        // Wheelbase Width = 0.5m
        TankModifier modifier = new TankModifier(trajectory).modify(0.5);

        // Do something with the new Trajectories...
        Trajectory left = modifier.getLeftTrajectory();
        Trajectory right = modifier.getRightTrajectory();

        EncoderFollower leftFollow = new EncoderFollower(left);
        leftFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        leftMain.set(leftFollow.calculate(leftMain.getSelectedSensorPosition(0)));

    }

}