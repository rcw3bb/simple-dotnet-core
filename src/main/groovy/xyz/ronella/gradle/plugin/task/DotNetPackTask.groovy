package xyz.ronella.gradle.plugin.task

/**
 * The task for creating a NuGet package.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetPackTask extends DotNetTask {
    public DotNetPackTask() {
        super()
        description = 'Create a NuGet package.'
        command = 'pack'
    }
}