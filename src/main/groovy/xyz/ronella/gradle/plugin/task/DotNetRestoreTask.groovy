package xyz.ronella.gradle.plugin.task

class DotNetRestoreTask extends DotNetTask {
    public DotNetRestoreTask() {
        command = 'restore'
    }
}