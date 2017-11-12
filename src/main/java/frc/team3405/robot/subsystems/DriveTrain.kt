package frc.team3405.robot.subsystems

import com.ctre.MotorControl.CANTalon
import frc.team3405.robot.commands.DriveCommand
import frc.team3405.robot.lib.BaseCommand
import frc.team3405.robot.lib.BaseSubSystem
import frc.team3405.robot.lib.Controller

/**
 * Created by ryanberger on 11/11/17.
 */


const val DEADZONE = .2
object DriveTrain : BaseSubSystem() {
    override val command: BaseCommand? = DriveCommand()

    private val frontRight: CANTalon = CANTalon(0)
    private val frontLeft: CANTalon = CANTalon(1)
    private val backLeft: CANTalon = CANTalon(2)
    private val backRight: CANTalon = CANTalon(3)

    val driveSystem by lazy { Drive(frontRight, frontLeft, backRight, backLeft) }

    fun arcadeDrive(controller: Controller) {
        driveSystem.arcadeDrive(controller.leftX, controller.leftY)
    }
}


infix fun Double.outsidePlusOrMinus(double: Double): Boolean {
    return double >= .2 && double <= -.2
}


class Drive(private val frontRight: CANTalon,
            private val frontLeft: CANTalon,
            private val backRight: CANTalon, private val backLeft: CANTalon) {
    var maxOutput = 1

    fun arcadeDrive(x: Double, y: Double) {
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput


        if (right outsidePlusOrMinus .2) {
            frontRight.set(right)
            backRight.set(right)
        }

        if (left outsidePlusOrMinus .2) {
            frontLeft.set(left)
            backLeft.set(left)
        }
    }

    fun tankDrive(x: Double, y: Double) {

    }


    fun driveDistance(distance: Double) {}

}