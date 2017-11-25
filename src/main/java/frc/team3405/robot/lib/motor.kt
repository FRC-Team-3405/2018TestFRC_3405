package frc.team3405.robot.lib

import edu.wpi.first.wpilibj.TalonSRX


/**
 * Created by ryanberger on 11/10/17.
 */

fun BaseSubsystem.motor(channel: Int): TalonSRX {

    return TalonSRX(channel)
}