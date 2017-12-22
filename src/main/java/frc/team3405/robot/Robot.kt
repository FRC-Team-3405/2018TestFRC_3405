package frc.team3405.robot
import frc.team3405.robot.subsystems.DriveTrain
import lib.BaseRobot
import lib.loopmanager.LoopManager

class Robot : BaseRobot() {
    lateinit var driveTrain: DriveTrain
    override fun operatorControl() { driveTrain = DriveTrain }

}