package frc.team3405.robot

import edu.wpi.first.wpilibj.command.Command
import frc.team3405.robot.subsystems.DriveTrain


class DriveCommand : Command() {

    init { requires(Robot.driveTrain) }


    override fun isFinished(): Boolean = false

    override fun execute() {
        print("Running")
        Robot.driveTrain.arcadeDrive(Robot.controller.leftX, Robot.controller.leftY, .7)
    }

}