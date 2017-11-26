package lib

import edu.wpi.first.wpilibj.command.Subsystem
import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubsystem: Subsystem() {
    open val loop: LifeCycleLoop? = null
    init { loop?.let { LoopManager.INSTANCE.addLifeCycleLoop(it) } }

    override fun initDefaultCommand() {}
}
