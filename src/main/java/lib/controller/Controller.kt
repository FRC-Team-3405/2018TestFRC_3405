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


open class Controller(val joystick: Joystick, bindings: List<Binding>) {

//    init { bind(bindings) }

//    private fun bind(bindings: List<Binding>) {
//        for (binding in bindings) {
//            // Get the port number
//            Button(joystick).apply { bind(binding) }
//        }
//    }
}


data class Binding(val mapping: Int) {
    lateinit var action: Action
    lateinit var state: State
}

infix fun Int.recieves(press: State): Binding = Binding(this).apply { state = press }
infix fun Binding.run(action: Action): Binding = this.apply { this.action = action }
infix fun Binding.then(list: MutableList<Binding>) { list.add(this) }

class BindingBuilder {
    val end: MutableList<Binding> = mutableListOf()
    fun finish(): MutableList<Binding> = end
}

