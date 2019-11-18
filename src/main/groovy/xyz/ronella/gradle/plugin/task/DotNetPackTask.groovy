package xyz.ronella.gradle.plugin.task

class DotNetPackTask extends DotNetTask {
    public DotNetPackTask() {
        command = 'pack'
    }
}