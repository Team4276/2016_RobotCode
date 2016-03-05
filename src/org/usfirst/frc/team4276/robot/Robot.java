
package org.usfirst.frc.team4276.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	TankDrive driver;
	Arm armer;
	LidarSpin spinny;
	LIDAR mylid;
	Shooter shoot;
	LEDOut lead;
	Encoder enc;
	Timer autotimer;
	
	public static int g_nSequenceLidar = 0;
	public static double g_lidarDistanceCentimeters = 0.0;
	
	public static int g_nSequenceVisionSystem = 0;
	public static boolean g_isVisionSystemGoalDetected = false;
	public static double g_visionSystemAngleRobotToGoal = -181.0;
	public static double g_visionSystemPixelX = -1.0;

	public static boolean g_isImuDataValid = false;
	public static double g_imuYawDegrees = -181.00;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

    	driver = new TankDrive(3,4,5,0,1,2,6,7); //RF,RM,RB,LF,LM,LB,DriveEnc1,DriveEnc2
    	armer = new Arm(6,7,3,0,1,2); //PowerMotor,IntakeMotor,BallStopLimit,ArmEnc,ArmEnc,HallSwitch
    	autotimer = new Timer();
    	//spinny = new LidarSpin(9);
    	//mylid = new LIDAR(Port.kMXP);
    	//mylid.start(20);
    	shoot = new Shooter(8,9,4,5); //ShooterLeft,ShooterRight,enc1,enc2
    	//lead = new LEDOut(9,8,7,6);
    	
    	
    	//JVisionSystemReceiverRunnable visionSystemRunnable = new JVisionSystemReceiverRunnable();
        //Thread visionSystemThread = new Thread(visionSystemRunnable);
        //visionSystemThread.start();   
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    	if(driver.autodrive(5, .4))
    	{
    		autotimer.reset();
    		autotimer.start();
    	}

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	
    	//SmartDashboard.putNumber("Encoder Value", enc.get());
    	armer.collector();
    	//spinny.spinnerex();
    	//driver.Powermode();
    	driver.Drive();
    	//driver.drive();
    	driver.fullpower();
    	shoot.run();
    	//lead.output();
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
