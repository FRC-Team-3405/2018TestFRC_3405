package frc.team3405.robot.controllers

import lib.controller.Binding
import lib.controller.Controller
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/25/17.
 */

class XboxController(port: Int, bindings: List<Binding>) : Controller(port, bindings) {
    val rightX
        get() = joystick.getRawAxis(Xbox.RightX)

    val rightY
        get() = joystick.getRawAxis(Xbox.RightY)

    val leftX
        get() = joystick.getRawAxis(Xbox.LeftX)

    val leftY
        get() = joystick.getRawAxis(Xbox.LeftY)
}