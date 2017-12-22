package lib.loopmanager

class LoopBuilder {
    private val loop: LifeCycleLoop = LifeCycleLoop()

    fun init(function: suspend () -> Unit) { loop.robotInit = function }
    fun teleop(function: suspend () -> Unit) { loop.onTeleop = function }
    fun autonomous(function: suspend () -> Unit) { loop.onAutonomous = function }

    fun build(): LifeCycleLoop = loop
}

fun lifeCycleLoop(buildLoop: LoopBuilder.() -> Unit): LifeCycleLoop {
    val builder = LoopBuilder()
    builder.buildLoop()
    return builder.build()
}