import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import edu.wpi.first.wpilibj.Joystick
import frc.team3405.robot.controllers.XboxController
import frc.team3405.robot.controllers.xboxController
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import lib.controller.State
import lib.maps.Xbox
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class ControllerTests {
    var timesExecuted: Int = 0

    @Before
    fun setUp() {
        timesExecuted = 0
    }

    @Test
    fun `holding test`() {
//        val joystick = mock<Joystick> {
//            on { getRawButton(Xbox.AButton) } doReturn true
//        }
//
//        xboxController(joystick) {
//            bind(Xbox.AButton, State.HELD) {
//                timesExecuted++
//            }
//        }
//
//        runBlocking {
//            delay(1, TimeUnit.SECONDS)
//        }
//
//        assertTrue(timesExecuted > 1)
    }
}