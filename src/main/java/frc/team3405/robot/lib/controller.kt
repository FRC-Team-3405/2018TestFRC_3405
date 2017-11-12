package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import frc.team3405.robot.lib.maps.Xbox

/**
 * Created by ryanberger on 11/11/17.
 */

enum class State {
    ACTIVE,
    INACTIVE,
    PRESSED,
    RELEASED,
    TOGGLE_PRESSED
}

class Controller(private val joystick: Joystick, bindings: List<Binding>) {
    val rightX
    get() = joystick.getRawAxis(Xbox.RightX)

    val rightY
    get() = joystick.getRawAxis(Xbox.RightY)

    val leftX
    get() = joystick.getRawAxis(Xbox.LeftX)

    val leftY
    get() = joystick.getRawAxis(Xbox.LeftY)

    val rightBumper
    get() = joystick.getRawAxis(Xbox.RightBumper)

    val leftBumper
    get() = joystick.getRawAxis(Xbox.LeftBumper)


    init {
        bind(bindings)
    }

    private fun bind(bindings: List<Binding>) {
        for (binding in bindings) {
            // Get the port number
            JoystickButton(joystick, binding.buttonPort).apply {
                when(binding.state) {
                    State.ACTIVE -> whenActive(binding.command)
                    State.INACTIVE -> whenInactive(binding.command)
                    State.PRESSED -> whenPressed(binding.command)
                    State.RELEASED -> whenReleased(binding.command)
                    State.TOGGLE_PRESSED -> toggleWhenPressed(binding.command)
                }
            }
        }
    }
}


data class Binding(val buttonPort: Int) {
    lateinit var command: BaseCommand
    lateinit var state: State
}

class ControllerBuilder {
    var port: Int = 0
    val exit: MutableList<Binding> = mutableListOf()
    val once: Int = 0
    fun build(): Controller {
        return Controller(Joystick(port), exit)
    }
}

infix fun Int.the(button: Int): Int {
    return button
}

infix fun Int.recieves(press: State): Binding {
    return Binding(this).apply { state = press }
}

infix fun Binding.run(baseCommand: BaseCommand): Binding {
    return this.apply { command = baseCommand }
}


infix fun Binding.and(list: MutableList<Binding>) {
    list.add(this)
}

fun controller(builder: ControllerBuilder.() -> Unit): Controller {
    return ControllerBuilder().apply {
        builder()
    }.build()
}