package xyz.ronella.gradle.plugin.task

/**
 * The task for running unit test for .NET project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetTestTask extends DotNetTask {
    public DotNetTestTask() {
        super()
        description = 'Run unit tests using the test runner specified in a .NET project.'
        command = 'test'
    }
}
