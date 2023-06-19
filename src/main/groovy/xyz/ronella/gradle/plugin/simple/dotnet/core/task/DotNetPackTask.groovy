package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for creating a NuGet package.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetPackTask extends DotNetTask {
    DotNetPackTask() {
        super()
        description = 'Create a NuGet package.'
        command = 'pack'
    }
}