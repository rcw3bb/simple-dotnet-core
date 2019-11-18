package xyz.ronella.gradle.plugin.task

class DotNetCleanTask extends DotNetTask {
    public DotNetCleanTask() {
        command = 'clean'
    }
}