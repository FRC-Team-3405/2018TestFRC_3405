package frc.team3405.robot.subsystems

import com.ctre.MotorControl.CANTalon
import frc.team3405.robot.OI
import frc.team3405.robot.controllers.XboxController
import lib.BaseSubsystem
import lib.loopmanager.LifeCycleLoop

/**
 * Created by ryanberger on 11/11/17.
 */


const val DEADZONE = .2
object DriveTrain : BaseSubsystem() {

    private val frontRight: CANTalon = CANTalon(0)
    private val frontLeft: CANTalon = CANTalon(1)
    private val backLeft: CANTalon = CANTalon(2)
    private val backRight: CANTalon = CANTalon(3)
    const val maxOutput = 1

    override val loop: LifeCycleLoop = (object: LifeCycleLoop() {
        override val onTeleop: suspend () -> Unit = {
            arcadeDrive(OI.pilotController)
        }
    })


    private fun arcadeDrive(controller: XboxController) {
        val x = controller.leftX
        val y = controller.leftY
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput

        frontRight.set(right)
        backRight.set(-right)

        frontLeft.set(-left)
        backLeft.set(left)
    }
}