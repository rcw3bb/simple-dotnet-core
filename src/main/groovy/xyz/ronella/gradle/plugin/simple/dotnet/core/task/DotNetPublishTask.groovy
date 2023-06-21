package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for publishing .Net project for deployment.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetPublishTask extends DotNetTask {
    DotNetPublishTask() {
        super()
        description = 'Publish a .NET project for deployment.'
        command.set('publish')
    }
}