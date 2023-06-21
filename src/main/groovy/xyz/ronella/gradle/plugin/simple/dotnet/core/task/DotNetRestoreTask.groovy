package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for restoring dependencies of the .NET project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetRestoreTask extends DotNetTask {
    DotNetRestoreTask() {
        super()
        description = 'Restore dependencies specified in a .NET project.'
        command.set('restore')
    }
}