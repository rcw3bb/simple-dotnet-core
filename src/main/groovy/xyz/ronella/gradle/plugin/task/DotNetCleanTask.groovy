package xyz.ronella.gradle.plugin.task

/**
 * The task for cleaning the build output.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetCleanTask extends DotNetTask {
    public DotNetCleanTask() {
        super()
        description = 'Clean build outputs of a .NET project.'
        command = 'clean'
    }
}