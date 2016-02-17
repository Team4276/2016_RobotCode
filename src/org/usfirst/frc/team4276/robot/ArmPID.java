package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

public class ArmPID extends Thread implements Runnable{
	
	DigitalInput SET_90_OPTICAL;
	
	public ArmPID()
	{
		SET_90_OPTICAL = new DigitalInput(5);	
	}
	
	public void run()
	{
		double angle,offset;
		double startang=90;
		
		double setpoint=startang;
		double k=.02,deadband=2;
		double power;
		
		try
		{
			while(true)
			{
				if (Arm.joystick.getRawButton(XBox.Start))
				{
					Arm.Pivoter.set(Arm.joystick.getRawAxis(XBox.LStickY));
				}
				else {
				angle=startang-Arm.enc.getDistance();
				
				offset = setpoint - angle;
				
				if(Math.abs(offset)>deadband)
				{
					power = k*offset;
				}
				else power=0;
				
				power+=Math.cos(angle)*.05; 
				Arm.Pivoter.set(power);
				if(Arm.joystick.getRawAxis(XBox.LStickY)>0.5)
					setpoint+=3;
				else if(Arm.joystick.getRawAxis(XBox.LStickY)<-0.5)
					setpoint-=3;
				
				if(Arm.joystick.getRawButton(XBox.X))
					setpoint=0;
				if(Arm.joystick.getRawButton(XBox.Y))
					setpoint=45;
				if(Arm.joystick.getRawButton(XBox.Start))
					setpoint=90;
				
				SmartDashboard.putNumber("Arm Offset: ", offset);
				SmartDashboard.putNumber("Setpoint: ", setpoint);
				SmartDashboard.putNumber("Power: ", power);
				SmartDashboard.putNumber("Arm Angle: ", angle);
				SmartDashboard.putBoolean("Switch", !SET_90_OPTICAL.get());
				
				if(!SET_90_OPTICAL.get())
				{
					Arm.enc.reset();
					angle=startang-Arm.enc.getDistance();
					setpoint = angle;
				}
			}
				Timer.delay(0.05);
				
				
			}
		}
		
		catch (Exception e)
		{
			
		}
	}

}