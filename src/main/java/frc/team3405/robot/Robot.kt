package frc.team3405.robot
import frc.team3405.robot.subsystems.DriveTrain
import lib.BaseRobot

class Robot : BaseRobot() {
    lateinit var driveTrain: DriveTrain
    override fun robotInit() {
        driveTrain = DriveTrain
    }
}