package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

public class Shooter {
	
	static VictorSP shootermotor1,shootermotor2;
	Joystick joy;
	AutoShoot autoshooter;
	Encoder shooterenc;
	boolean started=false;
	
	public Shooter(int shooterl,int shooterr,int shooterenc1,int shooterenc2)
	{
		shootermotor1 = new VictorSP(shooterl);
		shootermotor2 = new VictorSP(shooterr);
		shooterenc = new Encoder(shooterenc1,shooterenc2);
		joy=new Joystick(3);
		autoshooter = new AutoShoot();
	}
	
	void run()
	{
		SmartDashboard.putBoolean("Auto Shooter Started: ", autoshooter.isAlive());
		
		
		try{
			
		if(!autoshooter.isAlive())
				started=false;
		if(joy.getRawAxis(XBox.RTrigger)>0.5)
		{
			if(autoshooter.isAlive())
				autoshooter.interrupt();
			shootermotor1.set(1);
			shootermotor2.set(-1);
			started=false;
			
		}
		else if(joy.getRawAxis(XBox.RTrigger)<0.5&&!autoshooter.isAlive())
		{
			
			shootermotor1.set(0);
			shootermotor2.set(0);
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
		{
			shootermotor1.set(1);
			shootermotor2.set(-1);
			}
		else{
			shootermotor1.set(0);
			shootermotor2.set(0);
		}
	}

}
