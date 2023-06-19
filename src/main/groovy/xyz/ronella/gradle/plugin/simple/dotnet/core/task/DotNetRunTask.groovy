package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for building and running a .NET project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetRunTask extends DotNetTask {
    DotNetRunTask() {
        super()
        description = 'Build and run a .NET project output.'
        command = 'run'
    }
}