package xyz.ronella.gradle.plugin.task

class DotNetTestTask extends DotNetTask {
    public DotNetTestTask() {
        command = 'test'
    }
}
