package xyz.ronella.gradle.plugin.task

class DotNetBuildTask extends DotNetTask {
    public DotNetBuildTask() {
        super()
        description= 'Build a .Net project.'
        command = 'build'
    }
}
