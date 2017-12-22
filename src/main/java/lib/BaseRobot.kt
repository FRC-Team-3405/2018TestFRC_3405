package lib

import edu.wpi.first.wpilibj.SampleRobot
import lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */
open class BaseRobot : SampleRobot() {
    override fun robotInit() { LoopManager.INSTANCE.robotInit() }
    override fun autonomous() { LoopManager.INSTANCE.startAutonomous() }
    override fun operatorControl() { LoopManager.INSTANCE.startTeleop() }
    override fun disabled() { LoopManager.INSTANCE.disable() }
}