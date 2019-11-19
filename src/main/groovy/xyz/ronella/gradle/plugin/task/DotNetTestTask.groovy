package xyz.ronella.gradle.plugin.task

class DotNetTestTask extends DotNetTask {
    public DotNetTestTask() {
        super()
        description = 'Run unit tests using the test runner specified in a .NET project.'
        command = 'test'
    }
}
