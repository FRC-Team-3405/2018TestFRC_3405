package frc.team3405.robot.controllers

import edu.wpi.first.wpilibj.Joystick
import lib.controller.Binding
import lib.controller.BindingBuilder
import lib.controller.Controller
import lib.maps.Xbox

/**
 * Created by ryanberger on 11/25/17.
 */

class XboxController(joystick: Joystick, bindings: List<Binding>) : Controller(joystick, bindings) {
    val rightX
        get() = joystick.getRawAxis(Xbox.RightX)

    val rightY
        get() = joystick.getRawAxis(Xbox.RightY)

    val leftX
        get() = joystick.getRawAxis(Xbox.LeftX)

    val leftY
        get() = joystick.getRawAxis(Xbox.LeftY)
}

fun xboxController(port: Int, builder: BindingBuilder.() -> Unit): XboxController =
        XboxController(Joystick(port), BindingBuilder().apply { builder() }.finish())