package lib

import edu.wpi.first.wpilibj.command.Subsystem
import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubsystem: Subsystem() {
    protected var loop: LifeCycleLoop? = null
    set(value) {
        loop.let { LoopManager.INSTANCE.addLifeCycleLoop { value } }
        field = value
    }

    override fun initDefaultCommand() {}
}
