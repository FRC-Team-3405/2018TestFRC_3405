package frc.team3405.robot

import edu.wpi.first.wpilibj.Joystick
import frc.team3405.robot.controllers.XboxController
import frc.team3405.robot.controllers.xboxController
import lib.controller.*
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/11/17.
 */

object OI {
    val controller: XboxController = xboxController(0) {

    }
}
