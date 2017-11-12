package lib

import edu.wpi.first.wpilibj.TalonSRX


/**
 * Created by ryanberger on 11/10/17.
 */

fun BaseSubSystem.motor(channel: Int): TalonSRX {

    return TalonSRX(channel)
}