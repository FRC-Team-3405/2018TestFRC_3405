package lib.loopmanager

/**
 * Created by ryanberger on 11/25/17.
 */
open class LifeCycleLoop {
    open var robotInit: (suspend () -> Unit)? = null // this will run forever
    open var onAutonomous: (suspend () -> Unit)? = null // this will only run during autonomous
    open var onTeleop: (suspend () -> Unit)? = null // this only runs during teleop
}