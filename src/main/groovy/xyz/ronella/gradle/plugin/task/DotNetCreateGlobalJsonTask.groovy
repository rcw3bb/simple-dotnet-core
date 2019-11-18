package xyz.ronella.gradle.plugin.task

class DotNetCreateGlobalJsonTask extends DotNetTask {
    public DotNetCreateGlobalJsonTask() {
        command = 'new'
        args += 'globaljson'
    }
}