package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for running the MSBuild engine.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetMSBuildTask extends DotNetTask {
    public DotNetMSBuildTask() {
        super()
        description = 'Run Microsoft Build Engine (MSBuild) command.'
        command = 'msbuild'
    }
}