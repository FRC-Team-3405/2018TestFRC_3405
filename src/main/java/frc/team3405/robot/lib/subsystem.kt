package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.command.Subsystem

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubSystem(name: String, val command: BaseCommand? = null): Subsystem(name) {
    override fun initDefaultCommand() {
        command?.let {
            defaultCommand = command
        }
    }
}
