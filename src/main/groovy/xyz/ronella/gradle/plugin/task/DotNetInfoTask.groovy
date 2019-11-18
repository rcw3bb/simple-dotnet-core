package xyz.ronella.gradle.plugin.task

class DotNetInfoTask extends DotNetTask {
    public DotNetInfoTask() {
        args = ['--info']
    }
}