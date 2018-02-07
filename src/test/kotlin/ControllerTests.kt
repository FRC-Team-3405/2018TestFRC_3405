import org.junit.Before
import org.junit.Test

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