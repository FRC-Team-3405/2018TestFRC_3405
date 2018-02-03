package frc.team3405.robot

import edu.wpi.first.wpilibj.Joystick
import frc.team3405.robot.controllers.XboxController
import frc.team3405.robot.controllers.xboxController
import frc.team3405.robot.subsystems.DriveTrain
import lib.controller.*
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/11/17.
 */

object OI {
    val controller: XboxController = XboxController(Joystick(0), listOf())
}
