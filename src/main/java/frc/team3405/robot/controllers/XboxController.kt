package frc.team3405.robot.controllers

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.command.Scheduler
import kotlinx.coroutines.experimental.async
import lib.controller.Binding
import lib.controller.BindingBuilder
import lib.controller.Controller
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/25/17.
 */

class XboxController(val joystick: Joystick) {
    val rightX
        get() = joystick.getRawAxis(Xbox.RightX)

    val rightY
        get() = joystick.getRawAxis(Xbox.RightY)

    val leftX
        get() = joystick.getRawAxis(Xbox.LeftX)

    val leftY
        get() = joystick.getRawAxis(Xbox.LeftY)
}
