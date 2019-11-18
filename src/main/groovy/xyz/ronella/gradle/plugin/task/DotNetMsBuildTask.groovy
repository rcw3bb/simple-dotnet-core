package xyz.ronella.gradle.plugin.task

class DotNetMsBuildTask extends DotNetTask {
    public DotNetMsBuildTask() {
        command = 'msbuild'
    }
}