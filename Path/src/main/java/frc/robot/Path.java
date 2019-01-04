package frc.robot;

import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

public class Path{

    private Waypoint[] points;
    private EncoderFollower leftFollow, rightFollow;
    private Trajectory leftTrajectory, rightTrajectory, trajectory;
    private Trajectory.Config config;
    private TankModifier modifier;

    public Path(Waypoint[] points){
        this.points = points;
        generateTrajectory(this.points);
    }

    private void generateTrajectory(Waypoint[] points){

        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, Constants.kDt, Constants.kVelocity, Constants.kAcceleration, Constants.kJerk);

        trajectory = Pathfinder.generate(points, config);
        
        modifier = new TankModifier(trajectory).modify(Constants.robotWidth);

        leftTrajectory = modifier.getLeftTrajectory();
        rightTrajectory = modifier.getRightTrajectory();

        leftFollow = new EncoderFollower(leftTrajectory);
        leftFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        leftFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);

        rightFollow = new EncoderFollower(rightTrajectory);
        rightFollow.configureEncoder(0, 1024, Constants.kWheelDiameter);
        rightFollow.configurePIDVA(Constants.kP, Constants.kI, Constants.kD, Constants.kVelocityRatio, Constants.kAccelerationRatio);


    }

    public EncoderFollower getLeft(){
        return leftFollow;
    }

    public EncoderFollower getRight(){
        return rightFollow;
    }

}