package lib.loopmanager

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * Created by ryanberger on 11/25/17.
 */

/**
 * LoopManager
 *
 * Uses singleton pattern to provide only one instance to the application
 * We use this instead of an object because it is easier to setup and tear
 * down for testing.
 *
 * This class should handle any permutation of state in the robot.
 * The ideal robot state goes as follows:
 *
 * robotInit -> onAutonomous -> onTeleop
 *
 * We cannot guarantee this. It is possible for robot testing purposes, that you switch
 * between autonomous and teleop multiple times. You also must deal with the disable state
 * which can be an E-stop or the FRC making sure that you don't.
 *
 * The following happens at each lifecycle state:
 *
 * @robotInit -> Make sure that we set our inner state machine. Also, start up all of our
 * infinite loops. This "should" only happen once; Unless you have exceptions thrown in your
 * code
 *
 * @onAutonomous -> stop all previous lifecycle loops and start the autonomous ones. Make sure appropriate
 * infinite loops are running
 *
 * @onTeleop -> stop all previous lifecycle loops and start the teleop ones. Make sure appropriate infinite
 * loops are running
 *
 * @disable -> stop all loops.
 *
 * TODO: Either all infinite loops are running, or they all aren't. Make sure that happens
 */


class LoopManager {
    private var state: LifeCycleState = LifeCycleState.Disable()

    companion object {
        val INSTANCE: LoopManager by lazy { LoopManager() }
    }


    // lifecycle loops
    val lifeCycleLoops: MutableList<LifeCycleLoop> = mutableListOf()
    val runningLifeCycleLoops: MutableList<Job> = mutableListOf()

    // everything that is always running
    val infiniteLoops: MutableList<Loop> = mutableListOf()
    val runningInfiniteLoops: MutableList<Job> = mutableListOf()

    /**
     * @method addLifeCycleLoop
     * add a loop to be managed by the lifecycle engine
     *
     * @param makeLoop
     * lambda that will return a nullable lifecycle loop.
     * We might want to change this later, so that we don't
     * have to use lifeCycleLoop { } inside of another lambda
     * */
    fun addLifeCycleLoop(makeLoop: (() -> LifeCycleLoop?)) {

        // get our lifecycle loop
        val functionValue = makeLoop()

        functionValue?.let { loop ->
            /**
             * This ensures that if there is a race condition between
             * a state being set and a call to addLifeCycleLoop we don't accidentally
             * not run a lifecycle loop
             */
            when(state) {
                is LifeCycleState.Teleop -> {
                    loop.onTeleop?.let { runningLifeCycleLoops.add(launch { it() }) }
                }
                is LifeCycleState.Autonomous -> {
                    loop.onAutonomous?.let { runningLifeCycleLoops.add(launch { it() }) }
                }
            }
            // TODO make this call smarter. We should only do this when we have changed state once (don't do it before the robot has started)
            // the robot runs init as an infinite loop
            loop.robotInit?.let { runningInfiniteLoops.add(launch { it() }) }
            lifeCycleLoops.add(loop)
        }
    }

    fun addInfiniteLoop(loop: Loop) {
        infiniteLoops.add(loop)
    }

    fun robotInit() {
        state = LifeCycleState.Init()
        infiniteLoops.forEach { launch { it() } }
    }

    fun startAutonomous() {
        state = LifeCycleState.Autonomous()
        stopLifeCycleLoops()
        runningLifeCycleLoops += lifeCycleLoops.mapNotNull { it.onAutonomous }.map { launch { it() } }
    }

    fun startTeleop() {
        state = LifeCycleState.Teleop()
        stopLifeCycleLoops()
        runningLifeCycleLoops += lifeCycleLoops.mapNotNull { it.onTeleop }.map { launch { it() } }
    }

    fun disable () {
        state = LifeCycleState.Disable()
        runningInfiniteLoops.forEach { it.cancel() }
        runningLifeCycleLoops.forEach { it.cancel() }
        runningInfiniteLoops.removeAll { true }
        runningLifeCycleLoops.removeAll { true }
    }

    fun stopLifeCycleLoops() {
        runningLifeCycleLoops.forEach { it.cancel() }
        runningLifeCycleLoops.removeAll { true }
    }

}