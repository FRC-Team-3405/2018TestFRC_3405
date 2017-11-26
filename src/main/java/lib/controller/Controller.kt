package lib.controller

import edu.wpi.first.wpilibj.Joystick

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

// We will need a base controller that will allow us to create the bindings,
// and control all of our co-routines

// Extra logic such as getting the axes will done in the subclass so that we can
// maintain a minimal abstraction of our controller


open class Controller(port: Int, bindings: List<Binding>) {
    protected val joystick: Joystick = Joystick(port)

    init { bind(bindings) }

    private fun bind(bindings: List<Binding>) {
        for (binding in bindings) {
            // Get the port number

        }
    }
}


data class Binding(val buttonPort: Int) {
    lateinit var function: suspend () -> Unit
    lateinit var state: State
}
class ControllerBuilder<out T: Controller> {
    var port: Int = 0
    private val exit: MutableList<Binding> = mutableListOf()
    fun build(): T = Controller(port, exit) as T
}
infix fun Int.recieves(press: State): Binding = Binding(this).apply { state = press }
infix fun Binding.run(command: suspend () -> Unit): Binding = this.apply { function = command }
infix fun Binding.then(list: MutableList<Binding>) { list.add(this) }
inline fun <reified T: Controller> controller(builder: ControllerBuilder<T>.() -> Unit): T = ControllerBuilder<T>().apply { builder() }.build()
