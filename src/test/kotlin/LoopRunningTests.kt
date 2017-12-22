import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager
import lib.loopmanager.lifeCycleLoop
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LoopRunningTests {
    lateinit var loopManager: LoopManager

    @Before
    fun setUp() {
        loopManager = LoopManager()
    }

    @Test
    fun `teleop loop added after start actually runs`() {
        loopManager.startTeleop()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                teleop {}
            }
        }
        Assert.assertTrue(loopManager.lifeCycleLoopsLength() == 1)
    }

    @Test
    fun `autonomous loop added after start actually runs`() {
        loopManager.startAutonomous()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                autonomous {  }
            }
        }
        Assert.assertTrue(loopManager.lifeCycleLoopsLength() == 1)
    }

}