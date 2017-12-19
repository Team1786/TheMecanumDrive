package org.usfirst.frc.team1786.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.MotorControl.CANTalon;

public class Robot extends IterativeRobot {

	CANTalon leftTalonFront = new CANTalon(1);
	CANTalon leftTalonBack = new CANTalon(2);
	CANTalon rightTalonFront = new CANTalon(3);
	CANTalon rightTalonBack = new CANTalon(4);
	
	Joystick driverStick = new Joystick(0);
	
	public void mecanumDrive(double x, double y, double rotation, double Angle) {
		double inputX = x;
		double inputY = y;
		
		inputY = -inputY;
		
		double cosA = Math.cos(Angle * (3.14159 / 180.0));
		double sinA = Math.sin(Angle * (3.14159 / 180.0));
		
		double[] rotated = new double[2];
	    rotated[0] = inputX * cosA - inputY * sinA;
	    rotated[1] = inputX * sinA + inputY * cosA;
		
		double rotatedX = rotated[0];
		double rotatedY = rotated[1];
		
		leftTalonFront.set(rotatedX + rotatedY + rotation);
		leftTalonBack.set(-rotatedX + rotatedY + rotation);
		rightTalonFront.set(-rotatedX + );
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
	}

	@Override
	public void testPeriodic() {
	}
}

