/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    public static WPI_TalonSRX rightSlave, intake;
    
    EncoderFollower leftFollow;
    EncoderFollower rightFollow;

    Trajectory left, right;

    Trajectory.Config config;

    Waypoint[] points, points2;

    Trajectory trajectory;

    TankModifier modifier;

    int stage = 0;

    Timer testTime = new Timer();

    @Override
    public void robotInit() {

        intake = new WPI_TalonSRX(4);

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

        rightMain.config_kP(0, Constants.kP, Constants.kTimeoutMs);
        rightMain.config_kI(0, Constants.kI, Constants.kTimeoutMs);
        rightMain.config_kD(0, Constants.kD, Constants.kTimeoutMs);
        rightMain.config_kF(0, Constants.kF, Constants.kTimeoutMs);

        // config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.kDt, Constants.kVelocity, Constants.kAcceleration, Constants.kJerk);

        points = new Waypoint[] {
            new Waypoint(0, 0, 0),
            new Waypoint(1, 0, 0)
        };

        points2 = new Waypoint[] {
            new Waypoint(1, 0, 0),
            new Waypoint(2, 0, 0)
        };

        // trajectory = Pathfinder.generate(points, config);
        
        // modifier = new TankModifier(trajectory).modify(Constants.robotWidth);

        // left = modifier.getLeftTrajectory();
        // right = modifier.getRightTrajectory();

        // leftFollow = new EncoderFollower(left);
        // leftFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        // leftFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);

        // rightFollow = new EncoderFollower(right);
        // rightFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        // rightFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);

        test = new Path(points);
        test2 = new Path(points2);

    }

    Path test, test2;
    @Override
    public void autonomousInit() {
        leftMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
        rightMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
        testTime.start();
        testTime.reset();
    }

    @Override
    public void autonomousPeriodic() {
        // leftMain.set(ControlMode.PercentOutput, leftFollow.calculate(leftMain.getSelectedSensorPosition(0)));
        // rightMain.set(ControlMode.PercentOutput, -rightFollow.calculate(-(rightMain.getSelectedSensorPosition(0))));
        //System.out.println(leftFollow.isFinished());
        if(stage == 0){
            leftMain.set(ControlMode.PercentOutput, test.getLeft().calculate(leftMain.getSelectedSensorPosition(0)));
            rightMain.set(ControlMode.PercentOutput, -test.getRight().calculate(-rightMain.getSelectedSensorPosition(0)));
            if(test.getLeft().isFinished()){
                testTime.reset();
                stage = 1;
            }
        }
        else if(stage == 1){
            
            leftMain.set(ControlMode.PercentOutput, 0);
            rightMain.set(ControlMode.PercentOutput, 0);
            leftMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
            rightMain.setSelectedSensorPosition(0, 0, Constants.kTimeoutMs);
            if(testTime.get()<2){
                intake.set(0.7);
            }
            else{
                intake.set(0);
                stage = 2;
            }
        }
        else if(stage == 2){
            leftMain.set(ControlMode.PercentOutput, test2.getLeft().calculate(leftMain.getSelectedSensorPosition(0)));
            rightMain.set(ControlMode.PercentOutput, -test2.getRight().calculate(-rightMain.getSelectedSensorPosition(0)));
            if(test2.getLeft().isFinished()){
                stage = 3;
            }
        
        }
    }
    



}