package frc.team3405.robot.subsystems

import com.ctre.MotorControl.CANTalon
import edu.wpi.first.wpilibj.Talon
import frc.team3405.robot.OI
import frc.team3405.robot.controllers.XboxController
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import lib.BaseSubsystem
import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager
import lib.loopmanager.lifeCycleLoop

/**
 * Created by ryanberger on 11/11/17.
 */


const val DEADZONE = .2
object DriveTrain : BaseSubsystem() {

    private val frontRight: Talon = Talon(0)
    private val frontLeft: Talon = Talon(1)
    private val backLeft: Talon = Talon(2)
    private val backRight: Talon = Talon(3)
    private const val maxOutput = 1

    init {
        loop = lifeCycleLoop {
            init {
                while (true) {
                   arcadeDrive(OI.controller.leftX, OI.controller.leftY)
                }
            }
        }
    }


    fun arcadeDrive(controller: XboxController) {
        val x = controller.leftX
        val y = controller.leftY
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput

        frontRight.set(right)
        backRight.set(-right)

        frontLeft.set(-left)
        backLeft.set(left)
    }

    fun arcadeDrive(x: Double, y: Double) {
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput

        frontRight.set(right)
        backRight.set(right)

        frontLeft.set(-left)
        backLeft.set(-left)
    }
}