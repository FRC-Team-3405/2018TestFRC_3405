package frc.team3405.robot
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.SampleRobot
import edu.wpi.first.wpilibj.buttons.Button
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team3405.robot.controllers.XboxController
import frc.team3405.robot.subsystems.DriveTrain

import lib.BaseRobot
import lib.maps.Xbox
import java.util.concurrent.TimeUnit

class Robot : IterativeRobot() {

    companion object {
        val controller = XboxController(Joystick(0))
        val driveTrain = DriveTrain()
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }
}