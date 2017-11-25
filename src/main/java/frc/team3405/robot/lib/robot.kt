package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.SampleRobot
import edu.wpi.first.wpilibj.command.Scheduler
import frc.team3405.robot.lib.loopmanager.LoopManager

/**
 * Created by ryanberger on 11/3/17.
 */
abstract class BaseRobot : SampleRobot() {
    override fun robotInit() {}
    override fun autonomous() { LoopManager.startAutonomous() }
    override fun operatorControl() { LoopManager.startTeleop() }
}