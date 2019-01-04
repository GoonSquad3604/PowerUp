package frc.robot;

public class Constants{

    static final double kWheelDiameter = 0.1524; //6 inches
    
    static final int kTimeoutMs = 10;

    static final double kP = 0.7;
    static final double kI = 0.0;
    static final double kD = 0.0;
    static final double kF = 0.0;

    static final double kAcceleration = 3;//0.762; // m/s/s
    static final double kAccelerationRatio = 1/kAcceleration;
    static final double kVelocity = 3;//0.762; // m/s
    static final double kVelocityRatio = 1/kVelocity;
    static final double kDt = 0.05;
    static final double kJerk = 0.7;// m/s/s/s
    static final double robotWidth = 0.5588;

}