package xyz.ronella.gradle.plugin.task

class DotNetNewConsoleTask extends DotNetTask {
    public DotNetNewConsoleTask() {
        command = 'new'
        args = ['console']
    }
}
