package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for cleaning the build output.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetCleanTask extends DotNetTask {
    DotNetCleanTask() {
        super()
        description = 'Clean build outputs of a .NET project.'
        command.set('clean')
    }
}