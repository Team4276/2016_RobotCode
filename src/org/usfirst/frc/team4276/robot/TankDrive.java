package org.usfirst.frc.team4276.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class TankDrive {
	
	Talon FR;
	Talon MR;
	Talon BR;
	Talon FL;
	Talon ML;
	Talon BL;
	Joystick JR;
	Joystick JL;
	Timer time;
	double tick = Timer.getFPGATimestamp();
	double rightpower;
	double leftpower;
	double mode = 1;
	
	public TankDrive(int fr, int fl, int mr, int ml, int br, int bl)
	{
		FR = new Talon(fr);
		MR = new Talon(mr);
		BR = new Talon(br);
		FL = new Talon(fl);
		ML = new Talon(ml);
		BL = new Talon(bl);
		JL = new Joystick(0);
		JR = new Joystick(1);
		time = new Timer();
		time.reset();
		time.start();
		tick=time.get();
	}
	
	public void Drive()
	{
		
		
		
		if(Math.abs(JR.getY()) > .2 || Math.abs(JL.getY()) > .2)
		{
			rightpower = JR.getY();
			leftpower = JL.getY();
			
		}
		else
		{
			rightpower=0;
			leftpower=0;
			
		}
	}
	
	public void Powermode()
	{
		 
		 
		 if(time.get() - tick >= 10)
		 {
			 time.reset();
			 tick=0;
			 if(mode >= 3)
			 {
				mode = 1;
			
			 }
			 else
				 {
				 
				 
				 mode = mode + 1;
				 }
			 
		 }
		 if(mode >= 3)
		 {
			
			FR.set(rightpower);
			MR.set(rightpower);
			BR.set(0);
			FL.set(leftpower);
			ML.set(leftpower);
			BL.set(0);
		 }
		 if(mode == 2)
	 	 {
		 	FR.set(rightpower);
			MR.set(0);
			BR.set(rightpower);
			FL.set(leftpower);
			ML.set(0);
			BL.set(leftpower);
	 	 }
		 if(mode == 1)
		 {
			 	FR.set(0);
				MR.set(rightpower);
				BR.set(rightpower);
				FL.set(0);
				ML.set(leftpower);
				BL.set(leftpower);
		 }
		 	
	}
	public void fullpower()
	{
		FR.set(rightpower);
		MR.set(rightpower);
		BR.set(rightpower);
		FL.set(-leftpower);
		ML.set(-leftpower);
		BL.set(-leftpower);
	}
	public void drive()
	{
		if(!JR.getRawButton(1))
		{
			fullpower();
		}
		else
		{
			Powermode();
		}
	}
	
}
