package lib.loopmanager

class LoopBuilder {
    private val loop: LifeCycleLoop = LifeCycleLoop()

    fun init(function: suspend () -> Unit) { loop.robotInit = function}
    fun teleop(function: suspend () -> Unit) {}
    fun autonomous(function: suspend () -> Unit) {}

    fun build(): LifeCycleLoop = loop
}

fun lifeCycleLoop(buildLoop: LoopBuilder.() -> Unit): LifeCycleLoop {
    val builder = LoopBuilder()
    builder.buildLoop()
    return builder.build()
}