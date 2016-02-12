package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	
	Talon shootermotor;
	Joystick joy;
	
	public Shooter()
	{
		shootermotor = new Talon(9);
		joy=new Joystick(3);
	}
	
	void run()
	{
		if(joy.getRawButton(XBox.A))
		{
			shootermotor.set(1);
		}
		else if(joy.getRawButton(XBox.B))
		{
			shootermotor.set(0);
		}
	}

}
