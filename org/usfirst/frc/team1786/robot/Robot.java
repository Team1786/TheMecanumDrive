package org.usfirst.frc.team1786.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.MotorControl.CANTalon;

public class Robot extends IterativeRobot {

	// initialize CANTalons
	CANTalon leftTalonFront = new CANTalon(1);
	CANTalon leftTalonBack = new CANTalon(2);
	CANTalon rightTalonFront = new CANTalon(3);
	CANTalon rightTalonBack = new CANTalon(4);

	// initialize joystick
	Joystick driverStick = new Joystick(0);

	public void mecanumDrive(double x, double y, double rotation, double Angle) {
		double inputX = x;
		double inputY = y;

		// reverse inputs for flight joystick
		inputY = -inputY;

		// convert rotation angle to radians
		double cosA = Math.cos(Angle * (3.14159 / 180.0));
		double sinA = Math.sin(Angle * (3.14159 / 180.0));

		// rotate a vector of the two inputs to match the desired rotation
		double[] rotated = new double[2];
	    rotated[0] = inputX * cosA - inputY * sinA;
	    rotated[1] = inputX * sinA + inputY * cosA;

		// pull values out of rotated vector for ease of use
		double rotatedX = rotated[0];
		double rotatedY = rotated[1];

		// access motor controllers to set their voltage multipliers
		// the vectorvalues are added and subtracted to get the scaled values
		// for each wheel. Rotation is factored into the equation at the end
		leftTalonFront.set(rotatedX + rotatedY + rotation);
		leftTalonBack.set(-rotatedX + rotatedY + rotation);
		rightTalonFront.set(-rotatedX + rotatedY - rotation);
		rightTalonBack.set(rotatedX + rotatedY - rotation);
	}

	@Override
	public void robotInit() {

	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopPeriodic() {
		//get inputs from the user for driving
		double driverX = driverStick.getX();
		double driverY = driverStick.getY();
		double driverTwist = driverStick.getZ();

		// custom function for driving a mecanum drive train
		// TODO add the AHRS IMU data into this!
		mecanumDrive(driverX, driverY, driverTwist);
	}

	@Override
	public void testPeriodic() {
	}
}

