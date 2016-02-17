package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class Arm {
	  static Talon controller1;
	  static Talon controller2;
	  static Joystick joystick; 
	  static Encoder enc; 
	 static Encoder encode;
	 static Talon relayer;
	 static DigitalInput limiter;
	  boolean spinfor = false;
	  boolean spinaft = false;
	  double speed = 0;
	  Talon relay;
	  
	  
	
	public Arm (int power1, int power2, int rel, int lim) 
	{
		controller1 = new Talon ( power1);
		controller2 = new Talon ( power2);
		encode = new Encoder(2,3);
		encode.setDistancePerPulse(1);
		encode.reset();
		enc = new Encoder(0,1);
		enc.setDistancePerPulse(.362);
		enc.reset();
		joystick = new Joystick (3);
		relayer = new Talon(rel);
		limiter = new DigitalInput(lim);
		//relay = new Talon(rely);
		
		
	}

	
	static void Prime()
	{
		
		double actualDegree = 90 - enc.getDistance(); 
        double staticMotor =  Math.cos(actualDegree)*.30625; 
        double activeMotor = (joystick.getRawAxis(XBox.LStickY))/2; 
        double motorPower = activeMotor ; 
        
        controller1.set(-motorPower);
        controller2.set(motorPower);
        
        SmartDashboard.putNumber("Arm Degree", actualDegree);
        
	}
	
	
	public void collector()
	
	{
		double dif = Math.abs(speed) - Math.abs(encode.getRate());
		double k=.03;
		double deadband = .1;
		if(joystick.getRawButton(XBox.RB) && limiter.get())
		{
			spinfor = true;
			spinaft = false;
		}
		else if(joystick.getRawButton(XBox.LB))
		{
			spinfor=false;
			spinaft=true;	
		}
		else 
		{
			spinfor=false;
			spinaft=false;
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
		/*
		if (relayer.get()< 0)
		{
			if(joystick.getRawButton(XBox.A))
			{
				relayer.set(1);
				
			}
			if(joystick.getRawButton(XBox.Y))
			{
				relayer.set(0);
				
			}
		
		}
		if (relayer.get() == 0)
		{
			if(joystick.getRawButton(XBox.A))
			{
				relayer.set(1);
				
			}
			if(joystick.getRawButton(XBox.Y))
			{
				relayer.set(-1);
				
			}
		}
		if(joystick.getRawButton(XBox.X))
		{
		relayer.set(0);
		}*/
	}
public void Spinner()
	
	{
		if (relay.get() > 0)
		{
		if (joystick.getRawButton(XBox.LTrigger))
		{
			relay.set(0);
		}
		else if (joystick.getRawButton(XBox.RTrigger))
		{
			relay.set(-1);
		
		}
		}
		if (relay.get()< 0)
		{
			if(joystick.getRawButton(XBox.A))
			{
				relay.set(1);
				
			}
			if(joystick.getRawButton(XBox.Y))
			{
				relay.set(0);
				
			}
		
		}
		if (relay.get() == 0)
		{
			if(joystick.getRawButton(XBox.A))
			{
				relay.set(1);
				
			}
			if(joystick.getRawButton(XBox.Y))
			{
				relayer.set(-1);
				
			}
		}
		if(joystick.getRawButton(XBox.X))
		{
		relay.set(0);
		}
	}

static void autoRun(double speed)
{
	relayer.set(speed);
}
	
	
	
	

	
	
	
}