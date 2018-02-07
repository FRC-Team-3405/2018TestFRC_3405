package frc.team3405.robot.subsystems

import edu.wpi.first.wpilibj.Talon
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team3405.robot.DriveCommand
import frc.team3405.robot.controllers.XboxController

/**
 * Created by ryanberger on 11/11/17.
 */


const val DEADZONE = .2

class DriveTrain : Subsystem() {
    override fun initDefaultCommand() { DriveCommand() }

    private val frontRight: Talon = Talon(0)
    private val frontLeft: Talon = Talon(1)
    private val backLeft: Talon = Talon(2)
    private val backRight: Talon = Talon(3)

    init {
        arcadeDrive(0.0, -1.0)
        arcadeDrive(0.0, 0.0)
    }


    fun arcadeDrive(controller: XboxController, maxOutput: Double = 1.0) {
        val x = controller.leftX
        val y = controller.leftY
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput

        frontRight.set(right)
        backRight.set(right)

        frontLeft.set(-left)
        backLeft.set(-left)
    }

    fun arcadeDrive(x: Double, y: Double, maxOutput: Double = 1.0) {
        val left: Double = (y - x) * maxOutput
        val right: Double = (y + x) * maxOutput

        frontRight.set(right)
        backRight.set(right)

        frontLeft.set(-left)
        backLeft.set(-left)
    }

    fun tankDrive(left: Double, right: Double, maxOutput: Double = 1.0) {
        frontRight.set(right * maxOutput)
        backRight.set(right * maxOutput)

        frontLeft.set(-left * maxOutput)
        backLeft.set(-left * maxOutput)
    }
}