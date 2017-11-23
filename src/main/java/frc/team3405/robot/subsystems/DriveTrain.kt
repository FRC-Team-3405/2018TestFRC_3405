package frc.team3405.robot.subsystems

import com.ctre.MotorControl.CANTalon
import frc.team3405.robot.OI
import frc.team3405.robot.lib.BaseSubSystem
import frc.team3405.robot.lib.Controller
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

/**
 * Created by ryanberger on 11/11/17.
 */


const val DEADZONE = .2
object DriveTrain : BaseSubSystem("drivetrain") {

    private val frontRight: CANTalon = CANTalon(0)
    private val frontLeft: CANTalon = CANTalon(1)
    private val backLeft: CANTalon = CANTalon(2)
    private val backRight: CANTalon = CANTalon(3)
    const val maxOutput = 1

    val driveSystem by lazy { Drive(frontRight, frontLeft, backRight, backLeft) }

    fun arcadeDrive(controller: Controller) {
        val x = controller.leftX
        val y = controller.leftY
        val left: Double = (y + x) * maxOutput
        val right: Double = (y - x) * maxOutput

        frontRight.set(right)
        backRight.set(-right)

        frontLeft.set(-left)
        backLeft.set(left)
    }

    init {
        print("Hello")
        async {
            while (true) {
                arcadeDrive(OI.controller)
                delay(100)
            }
        }
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

        print(right)
        print(left)

        frontRight.set(right)
        backRight.set(-right)

//        frontLeft.set(left)
//        backLeft.set(left)

        if (right outsidePlusOrMinus DEADZONE) {
            print("Yes")
        }

        if (left outsidePlusOrMinus DEADZONE) {
            print("Yes")
        }
    }

    fun tankDrive(x: Double, y: Double) {

    }


    fun driveDistance(distance: Double) {}

}