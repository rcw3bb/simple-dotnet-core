package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for running the MSBuild engine.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetMSBuildTask extends DotNetTask {
    DotNetMSBuildTask() {
        super()
        description = 'Run Microsoft Build Engine (MSBuild) command.'
        command.set('msbuild')
    }
}