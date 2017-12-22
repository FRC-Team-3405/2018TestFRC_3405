import lib.loopmanager.LifeCycleLoop
import lib.loopmanager.LoopManager
import lib.loopmanager.lifeCycleLoop
import org.junit.Assert
import org.junit.Before
import org.junit.Test


/**
 * Created by ryanberger on 11/25/17.
 */


class LoopTests {
    lateinit var loopManager: LoopManager

    @Before
    fun setup() {
        loopManager = LoopManager()
    }

    @Test
    fun `no loops running`() {
        assert(loopManager.infiniteLoopsLength() == 0)
    }

    @Test
    fun `loops don't run yet`() {
        loopManager.addInfiniteLoop {  }
        Assert.assertTrue(loopManager.infiniteLoopsLength() != 1)
    }


    @Test
    fun `robotInit life cycle loops run forever`() {
        loopManager.addLifeCycleLoop {
            lifeCycleLoop {
                init {  }
            }
        }
        loopManager.robotInit()
        loopManager.startAutonomous()
        Assert.assertTrue(loopManager.infiniteLoopsLength() == 1)
    }

}

