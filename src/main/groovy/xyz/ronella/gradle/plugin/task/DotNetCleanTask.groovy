package xyz.ronella.gradle.plugin.task

class DotNetCleanTask extends DotNetTask {
    public DotNetCleanTask() {
        super()
        description = 'Clean build outputs of a .NET project.'
        command = 'clean'
    }
}