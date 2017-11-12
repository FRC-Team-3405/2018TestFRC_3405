package lib

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler

/**
 * Created by ryanberger on 11/3/17.
 */
abstract class BaseRobot : IterativeRobot() {
    override fun robotInit() {
        Scheduler.getInstance().run()
    }
    override fun disabledInit() {}
    override fun autonomousInit() {}
    override fun teleopInit() {}
}