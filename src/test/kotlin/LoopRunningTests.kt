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
                teleop {  }
            }
        }
        Assert.assertTrue(loopManager.runningLifeCycleLoops.size == 1)
    }

    @Test
    fun `autonomous loop added after start actually runs`() {
        loopManager.startAutonomous()
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                autonomous {  }
            }
        }
        Assert.assertTrue(loopManager.runningLifeCycleLoops.size == 1)
    }

    @Test
    fun `init loop added after runs forever`() {
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                init {  }
            }
        }
        loopManager.robotInit()
        loopManager.startTeleop()
        Assert.assertTrue("Size: ${loopManager.runningInfiniteLoops.size}", loopManager.runningInfiniteLoops.size == 1)
    }

    @Test
    fun `infinite loops don't start until after robotInit`() {
        loopManager.addInfiniteLoop {  }
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                init {  }
            }
        }

        Assert.assertTrue(loopManager.runningInfiniteLoops.isEmpty())
    }

    @Test
    fun `infinite loops are renabled`() {
        loopManager.addInfiniteLoop {  }
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                init {  }
            }
        }

        loopManager.robotInit()
        loopManager.startAutonomous()
        loopManager.disable()
        loopManager.startTeleop()
        Assert.assertTrue(loopManager.runningInfiniteLoops.size == 2)
    }

    @Test
    fun `infinite loops added after disabled are run`() {
        loopManager.robotInit()
        loopManager.startAutonomous()
        loopManager.disable()

        loopManager.addInfiniteLoop {  }
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                init {  }
            }
        }

        loopManager.startTeleop()
        Assert.assertTrue(loopManager.runningInfiniteLoops.size == 2)
    }



}