package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for creating a C# console application.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetNewConsoleTask extends DotNetTask {
    DotNetNewConsoleTask() {
        super()
        description = 'Create a new .NET Console project.'
        command='new'
        internalArgs.add('console')
    }
}
