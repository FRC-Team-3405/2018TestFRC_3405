package lib

import edu.wpi.first.wpilibj.SampleRobot
import lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */
abstract class BaseRobot : SampleRobot() {
    private val loopManager = LoopManager.INSTANCE

    override fun robotInit() {}
    override fun autonomous() { loopManager.startAutonomous() }
    override fun operatorControl() { loopManager.startTeleop() }
    override fun disabled() { loopManager.disable() }
}