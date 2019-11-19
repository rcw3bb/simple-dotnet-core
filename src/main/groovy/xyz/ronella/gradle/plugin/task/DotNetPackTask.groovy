package xyz.ronella.gradle.plugin.task

class DotNetPackTask extends DotNetTask {
    public DotNetPackTask() {
        super()
        description = 'Create a NuGet package.'
        command = 'pack'
    }
}