import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager
import lib.loopmanager.lifeCycleLoop
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoopDeathTests {
    lateinit var loopManager: LoopManager

    @Before
    fun setUp() {
        loopManager = LoopManager()
    }

    @Test
    fun `autonomous loop gets disabled`() {
        loopManager.startAutonomous()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                autonomous {  }
            }
        }
        loopManager.disable()
        Assert.assertTrue(loopManager.lifeCycleLoopsLength() == 0)
    }
}