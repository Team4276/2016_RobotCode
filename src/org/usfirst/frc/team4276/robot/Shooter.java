package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Talon;

public class Shooter {
	
	static Talon shootermotor;
	Joystick joy;
	AutoShoot autoshooter;
	boolean started=false;
	
	public Shooter()
	{
		shootermotor = new Talon(8);
		joy=new Joystick(3);
		autoshooter = new AutoShoot();
	}
	
	void run()
	{
		SmartDashboard.putBoolean("Auto Shooter Started: ", autoshooter.isAlive());
		
		
		try{
			
		if(!autoshooter.isAlive())
				started=false;
		if(joy.getRawButton(XBox.A))
		{
			if(autoshooter.isAlive())
				autoshooter.interrupt();
			shootermotor.set(1);
			started=false;
			
		}
		else if(joy.getRawButton(XBox.B))
		{
			if(autoshooter.isAlive())
				autoshooter.interrupt();
			shootermotor.set(0);
			started=false;
			
		}
		else if(joy.getRawAxis(XBox.LTrigger)>0.5&&!started)
		{
			SmartDashboard.putBoolean("Exception Thrown: ", false);
			started=true;
			autoshooter=new AutoShoot();
			autoshooter.start();
			
		}}
		
		catch(Exception e){
			SmartDashboard.putBoolean("Exception Thrown: ", true);
		}
	}

	static void set(boolean shooteron)
	{
		if(shooteron)
			shootermotor.set(1);
		else
			shootermotor.set(0);
	}

}
