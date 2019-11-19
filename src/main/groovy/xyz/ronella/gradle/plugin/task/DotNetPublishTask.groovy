package xyz.ronella.gradle.plugin.task

class DotNetPublishTask extends DotNetTask {
    public DotNetPublishTask() {
        super()
        description = 'Publish a .NET project for deployment.'
        command = 'publish'
    }
}