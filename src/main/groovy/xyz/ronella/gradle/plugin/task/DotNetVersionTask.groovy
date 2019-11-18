package xyz.ronella.gradle.plugin.task

class DotNetVersionTask extends DotNetTask {
    public DotNetVersionTask() {
        args = ['--version']
    }
}
