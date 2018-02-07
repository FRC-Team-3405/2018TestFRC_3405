package lib.controller

import edu.wpi.first.wpilibj.Joystick

/**
 * Created by ryanberger on 11/11/17.
 */

enum class State {
    HELD,
    TOGGLED
}


typealias Action = suspend () -> Unit

// We will need a base controller that will allow us to create the bindings,
// and control all of our co-routines

// Extra logic such as getting the axes will done in the subclass so that we can
// maintain a minimal abstraction of our controller


open class Controller(val joystick: Joystick) {

}


data class Binding(val mapping: Int, val state: State, val action: Action)

class BindingBuilder {
    private val bindings: MutableList<Binding> = mutableListOf()
    fun bind(port: Int, state: State, action: Action) { bindings.add(Binding(port, state, action)) }
    fun build() = bindings
}

