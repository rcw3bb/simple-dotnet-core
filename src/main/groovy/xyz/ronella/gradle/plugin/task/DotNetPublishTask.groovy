package xyz.ronella.gradle.plugin.task

/**
 * The task for publishing .Net project for deployment.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetPublishTask extends DotNetTask {
    public DotNetPublishTask() {
        super()
        description = 'Publish a .NET project for deployment.'
        command = 'publish'
    }
}