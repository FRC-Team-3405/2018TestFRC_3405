package frc.team3405.robot
import frc.team3405.robot.subsystems.DriveTrain
import frc.team3405.robot.lib.BaseRobot

class Robot : BaseRobot() {
    companion object {
        lateinit var driveTrain: DriveTrain
    }

    override fun operatorControl() {
        driveTrain = DriveTrain
    }
}