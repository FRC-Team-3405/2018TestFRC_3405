package frc.team3405.robot.commands

import frc.team3405.robot.OI
import frc.team3405.robot.Robot
import frc.team3405.robot.subsystems.DriveTrain
import lib.BaseCommand


/**
 * Created by ryanberger on 11/11/17.
 */

class DriveCommand : BaseCommand() {
    init {
        requires(Robot.driveTrain)
    }

    override fun execute() {
        Robot.driveTrain.arcadeDrive(OI.controller)
    }
}