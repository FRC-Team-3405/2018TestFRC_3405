package lib

import edu.wpi.first.wpilibj.command.Subsystem

/**
 * Created by ryanberger on 11/3/17.
 */

abstract class BaseSubSystem(val c: BaseCommand = command {}): Subsystem() {
    override fun initDefaultCommand() {
        defaultCommand = c
    }
}
