package frc.team3405.robot

import edu.wpi.first.wpilibj.Joystick
import lib.*
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/11/17.
 */

object OI {
    private val pilotJoystick = Joystick(0)

    val controller = controller {
        joystick = pilotJoystick
        once the Xbox.AButton recieves State.PRESSED run command { } and exit
    }
}
