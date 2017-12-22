package lib.controller

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.buttons.JoystickButton
import kotlinx.coroutines.experimental.launch

/**
 * Created by ryanberger on 11/25/17.
 */

class Button(val joystick: Joystick) {

    private val bindings: MutableList<Binding> = mutableListOf()
    fun bind(binding: Binding) {
        bindings.add(binding)
        when (binding.state) {
            State.HELD -> held(binding.mapping, binding.action)
            State.TOGGLED -> toggled(binding.mapping, binding.action)
        }
    }

    private fun held(mapping: Int, action: Action) {
        launch {
            val button = JoystickButton(joystick, mapping)
            while (true) {
                if (button.get()) {
                    launch { action() }
                }
            }
        }
    }

    fun toggled(mapping: Int, action: Action) {
        launch {
            val button = JoystickButton(joystick, mapping)
            var lock: Boolean = false
            while (true) {
                if (button.get()) {
                    lock = true
                } else {
                    if (lock) {
                        lock = false
                        launch { action() }
                    }
                }
            }
        }
    }
}