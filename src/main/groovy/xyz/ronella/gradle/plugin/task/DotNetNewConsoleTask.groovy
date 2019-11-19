package xyz.ronella.gradle.plugin.task

class DotNetNewConsoleTask extends DotNetTask {
    public DotNetNewConsoleTask() {
        super()
        description = 'Create a new .NET Console project.'
        command = 'new'
        internalArgs += 'console'
    }
}
