package xyz.ronella.gradle.plugin.task

class DotNetPublishTask extends DotNetTask {
    public DotNetPublishTask() {
        command = 'publish'
    }
}