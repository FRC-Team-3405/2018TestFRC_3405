package lib.controller

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import edu.wpi.first.wpilibj.command.Command
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by ryanberger on 11/25/17.
 */

class Button(val joystick: Joystick) {

    private val bindings: MutableList<Binding> = mutableListOf()

    fun bind(binding: Binding) {
        bindings.add(binding)
        when (binding.state) {
            State.HELD -> JoystickButton(joystick, binding.mapping).apply {
                whileHeld(object: Command (){
                    override fun execute() {
                        print("Hllo")
                        launch { binding.action() }
                    }
                    override fun isFinished(): Boolean = true
                })
            }
//            State.TOGGLED -> toggled(binding.mapping, binding.action)action
        }
    }
}