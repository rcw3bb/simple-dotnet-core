package xyz.ronella.gradle.plugin.task

class DotNetInfoTask extends DotNetTask {
    public DotNetInfoTask() {
        super()
        description = 'Display .NET Core information.'
        internalArgs += ['--info']
    }
}