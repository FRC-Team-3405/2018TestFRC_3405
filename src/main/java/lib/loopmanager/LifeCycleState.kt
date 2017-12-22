package lib.loopmanager


sealed class LifeCycleState {
    class Init: LifeCycleState()
    class Autonomous: LifeCycleState()
    class Teleop: LifeCycleState()
    class Disable: LifeCycleState()
}