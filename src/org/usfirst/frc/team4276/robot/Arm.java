package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm {
	  static Talon Pivoter;
	  static Joystick joystick; 
	  static Encoder enc; 
	 static Encoder encode;
	 static Talon relayer;
	 static DigitalInput limiter;
	  boolean spinfor = false;
	  boolean spinaft = false;
	  double speed = 0;
	  Talon relay;
	  
	  
	
	public Arm (int power, int rel, int lim) 
	{
		Pivoter = new Talon ( power);
		enc = new Encoder(0,1);
		enc.setDistancePerPulse(.362);
		enc.reset();
		encode = new Encoder(8,9);
		encode.setDistancePerPulse(1);
		encode.reset();
		joystick = new Joystick (3);
		relayer = new Talon(rel);
		limiter = new DigitalInput(lim);
		
		
	}
	
	
	public void collector()
	
	{
		SmartDashboard.putBoolean("LIMIT SWITCH", limiter.get());
		double dif = Math.abs(speed) - Math.abs(encode.getRate());
		double k=.03;
		double deadband = .1;
		if(joystick.getRawButton(XBox.RB) && !limiter.get())
		{
			spinfor = true;
			spinaft = false;
			joystick.setRumble(RumbleType.kLeftRumble, 0);
			joystick.setRumble(RumbleType.kLeftRumble, 0);
		}
		else if(joystick.getRawButton(XBox.RB) && limiter.get())
		{
			spinfor=false;
			spinaft=false;
			joystick.setRumble(RumbleType.kLeftRumble, 1);
			joystick.setRumble(RumbleType.kLeftRumble, 1);
		}
		else if(joystick.getRawButton(XBox.LB))
		{
			spinfor=false;
			spinaft=true;	
			joystick.setRumble(RumbleType.kLeftRumble, 0);
			joystick.setRumble(RumbleType.kLeftRumble, 0);
		}
		else 
		{
			spinfor=false;
			spinaft=false;
			joystick.setRumble(RumbleType.kLeftRumble, 0);
			joystick.setRumble(RumbleType.kLeftRumble, 0);
		}
		
		 
		if(spinfor)
		{
			if(speed == .5 || speed == 0 ||speed == -.5)
			{
			speed = .5;
			}
		}
		else if(spinaft)
		{
			if(speed == .5 || speed == 0 ||speed == -.5)
			{
			speed = -.5;
			}
		}
		else
		{
			speed = 0;
		}
		
			if(dif > deadband && speed > 0)
		{
			speed+=dif*k;
		}
			else if(dif > deadband && speed < 0)
			{
				speed-=dif*k;
			}
		
		relayer.set(speed);
		
	}


static void autoRun(double speed)
{
	relayer.set(speed);
}
	
	
	
	

	
	
	
}