package xyz.ronella.gradle.plugin.task

/**
 * The task for knowing the .Net Core information on your machine.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetInfoTask extends DotNetTask {
    public DotNetInfoTask() {
        super()
        description = 'Display .NET Core information.'
        internalArgs += '--info'
    }
}