import frc.team3405.robot.lib.loopmanager.LifeCycleLoop
import frc.team3405.robot.lib.loopmanager.LoopManager
import org.junit.Assert
import org.junit.Test


/**
 * Created by ryanberger on 11/25/17.
 */


class LoopTests {
    @Test
    fun `no loops running`() {
        val loopManager = LoopManager()
        assert(loopManager.infiniteLoopsLength() == 0)
    }

    @Test
    fun `loops don't run yet`() {
        val loopManager = LoopManager()
        loopManager.addInfiniteLoop {  }
        Assert.assertTrue(loopManager.infiniteLoopsLength() != 1)
    }

    @Test
    fun `loops run after init`() {
        val loopManager = LoopManager()
        loopManager.addLifeCycleLoop((object: LifeCycleLoop() {
            override val robotInit: (suspend () -> Unit) = {}
        }))
        loopManager.robotInit()
        Assert.assertTrue(loopManager.infiniteLoopsLength() == 1)
    }
}

