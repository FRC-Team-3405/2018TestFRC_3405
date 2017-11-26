package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3405.robot.lib.loopmanager.LifeCycleLoop
import frc.team3405.robot.lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubsystem(name: String): Subsystem(name) {
    abstract val loop: LifeCycleLoop

    init {
        LoopManager.INSTANCE.addLifeCycleLoop(loop)
    }

    override fun initDefaultCommand() {}
}
