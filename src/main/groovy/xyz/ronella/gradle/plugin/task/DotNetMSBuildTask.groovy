package xyz.ronella.gradle.plugin.task

class DotNetMSBuildTask extends DotNetTask {
    public DotNetMSBuildTask() {
        super()
        description = 'Run Microsoft Build Engine (MSBuild) command.'
        command = 'msbuild'
    }
}