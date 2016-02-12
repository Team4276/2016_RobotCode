package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder; 
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	  Talon controller1;
	  Talon controller2;
	  Joystick joystick; 
	  Encoder enc; 
	  Talon relayer;
	  boolean spinfor = false;
	  boolean spinaft = false;
	  boolean pressed=false;
	  Talon relay;
	  
	  
	
	public Arm (int power1, int power2, int rel) 
	{
		controller1 = new Talon ( power1);
		controller2 = new Talon ( power2);
		enc = new Encoder(0,1);
		enc.setDistancePerPulse(.362);
		enc.reset();
		joystick = new Joystick (3);
		relayer = new Talon(rel);
		//relay = new Talon(rely);
		
		
	}

	
	public void Prime()
	{
		
		double actualDegree = 90 - enc.getDistance(); 
        double staticMotor =  Math.cos(actualDegree)*.30625; 
        double activeMotor = (joystick.getRawAxis(XBox.LStickY))/2; 
        double motorPower = activeMotor ; 
        
        controller1.set(-motorPower);
        controller2.set(motorPower);
        
        SmartDashboard.putNumber("Arm Degree", actualDegree);
        
	}
	
	public void Spin()
	
	{
		if((joystick.getRawButton(XBox.LB)||joystick.getRawButton(XBox.RB))&&!pressed)
		{
			pressed=true;
			
			if(joystick.getRawButton(XBox.LB))
			{
					spinfor=!spinfor;
					spinaft=false;
			}
			else if(joystick.getRawButton(XBox.RB))
					{
							spinaft=!spinaft;
							spinfor=false;
					}
		}
		else if((!joystick.getRawButton(XBox.LB))&&(!joystick.getRawButton(XBox.RB)))
		{
			pressed=false;
		}
		
				
		
		if(joystick.getRawAxis(XBox.RTrigger)>0.5)
		{
			relayer.set(1);}
			else{
		if(spinfor)
		{
			relayer.set(.5);
		}
		else if(spinaft)
		{
			relayer.set(-.5);
		}
		else relayer.set(0);
		
		
	}
		/*if (relayer.get() > 0)
		{
		if (joystick.getRawButton(XBox.A))
		{
			relayer.set(0);
			;
		}
		else if (joystick.getRawButton(XBox.Y))
		{
			relayer.set(-1);
		
		}
		}
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
			;
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
	
	
	
	

	
	
	
}