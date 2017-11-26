package lib.loopmanager

/**
 * Created by ryanberger on 11/25/17.
 */
open class LifeCycleLoop {
    open val robotInit: (suspend () -> Unit)? = null // this will run forever
    open val onAutonomous: (suspend () -> Unit)? = null // this will only run during autonomous
    open val onTeleop: (suspend () -> Unit)? = null
}