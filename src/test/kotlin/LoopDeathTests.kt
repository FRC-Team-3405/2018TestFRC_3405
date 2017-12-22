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
        Assert.assertTrue(loopManager.runningLifeCycleLoops.isEmpty())
    }

    @Test
    fun `autonomous loop gets disabled on teleop`() {
        loopManager.startAutonomous()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                autonomous {  }
            }
        }

        loopManager.startTeleop()
        Assert.assertTrue(loopManager.runningLifeCycleLoops.isEmpty())
    }

    @Test
    fun `autonomous gets re-enabled after being disabled`() {
        loopManager.startAutonomous()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                autonomous {  }
            }
        }

        loopManager.disable()
        loopManager.startAutonomous()
        Assert.assertTrue("Loop size ${loopManager.runningLifeCycleLoops.size}", loopManager.runningLifeCycleLoops.size == 1)
    }

}