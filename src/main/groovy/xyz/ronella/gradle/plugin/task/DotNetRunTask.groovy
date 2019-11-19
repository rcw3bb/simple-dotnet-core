package xyz.ronella.gradle.plugin.task

class DotNetRunTask extends DotNetTask {
    public DotNetRunTask() {
        super()
        description = 'Build and run a .NET project output.'
        command = 'run'
    }
}