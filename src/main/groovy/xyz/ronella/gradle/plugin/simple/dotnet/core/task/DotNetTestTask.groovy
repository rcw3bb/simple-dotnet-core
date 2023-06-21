package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for running unit test for .NET project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetTestTask extends DotNetTask {
    DotNetTestTask() {
        super()
        description = 'Run unit tests using the test runner specified in a .NET project.'
        command.set('test')
    }
}
