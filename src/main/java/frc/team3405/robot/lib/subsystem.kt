package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.command.Subsystem

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubSystem: Subsystem() {
    open val command: BaseCommand? = null
    override fun initDefaultCommand() {
        command?.let {
            defaultCommand = it
        }
    }
}
