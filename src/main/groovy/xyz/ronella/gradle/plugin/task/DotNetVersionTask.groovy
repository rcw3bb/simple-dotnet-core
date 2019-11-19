package xyz.ronella.gradle.plugin.task

class DotNetVersionTask extends DotNetTask {
    public DotNetVersionTask() {
        super()
        description = 'Display .NET Core SDK version is use.'
        internalArgs += '--version'
    }
}
