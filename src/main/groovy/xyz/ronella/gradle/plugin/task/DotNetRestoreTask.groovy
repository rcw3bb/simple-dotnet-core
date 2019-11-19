package xyz.ronella.gradle.plugin.task

class DotNetRestoreTask extends DotNetTask {
    public DotNetRestoreTask() {
        super()
        description = 'Restore dependencies specified in a .NET project.'
        command = 'restore'
    }
}