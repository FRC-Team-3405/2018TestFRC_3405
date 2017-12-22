package lib.loopmanager

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by ryanberger on 11/25/17.
 */

class LoopManager {
    private var state: LifeCycleState = LifeCycleState.Disable()

    companion object {
        private var instance: LoopManager? = null

        val INSTANCE: LoopManager
        get() {
            if(instance == null) {
                instance = LoopManager()
            }
            return instance!!
        }
    }


    // lifecycle loops
    private val lifeCycleLoops: MutableList<LifeCycleLoop> = mutableListOf()
    private val runningLifeCycleLoops: MutableList<Job> = mutableListOf()

    // everything that is always running
    private val infiniteLoops: MutableList<Loop> = mutableListOf()
    private val runningInfiniteLoops: MutableList<Job> = mutableListOf()

    fun addLifeCycleLoop(makeLoop: (() -> LifeCycleLoop?)) {
        val functionValue = makeLoop()

        functionValue?.let { loop ->
            when(state) {
                is LifeCycleState.Teleop -> {
                    runningLifeCycleLoops.add(launch { loop.onTeleop?.invoke() })
                }
                is LifeCycleState.Autonomous -> {
                    runningLifeCycleLoops.add(launch { loop.onAutonomous?.invoke() })

                }
                is LifeCycleState.Init -> {
                    runningLifeCycleLoops.add(launch { loop.robotInit?.invoke() })
                }
            }

            lifeCycleLoops.add(loop)
        }
    }

    fun addInfiniteLoop(loop: Loop) {
        infiniteLoops.add(loop)
    }

    fun robotInit() {
        // start all of our lifecycle magic
        state = LifeCycleState.Init()
        lifeCycleLoops
                .mapNotNull { it.robotInit }
                .forEach { runningInfiniteLoops.add(launch { it() }) }

        infiniteLoops.forEach { launch { it() } }
        println("Launched all of them")
    }

    fun startAutonomous() {
        state = LifeCycleState.Autonomous()
        runningLifeCycleLoops += lifeCycleLoops.mapNotNull { it.onAutonomous }.map { launch { it() } }
    }

    fun startTeleop() {
        state = LifeCycleState.Teleop()
        runningLifeCycleLoops.forEach { it.cancel() }
        runningLifeCycleLoops.removeAll { true }
        runningLifeCycleLoops += lifeCycleLoops.mapNotNull { it.onTeleop }.map { launch { it() } }
    }

    fun disable () {
        state = LifeCycleState.Disable()
        runningInfiniteLoops.forEach { it.cancel() }
        runningLifeCycleLoops.forEach { it.cancel() }
        runningInfiniteLoops.removeAll { true }
        runningLifeCycleLoops.removeAll { true }
    }

    fun stopAllLoops() {}

    // testing methods
    fun lifeCycleLoopsLength() = runningLifeCycleLoops.size
    fun infiniteLoopsLength() = runningInfiniteLoops.size
}