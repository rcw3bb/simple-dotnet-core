package xyz.ronella.gradle.plugin.task

/**
 * The task for knowing the current SDK version being used.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetVersionTask extends DotNetTask {
    public DotNetVersionTask() {
        super()
        description = 'Display .NET Core SDK version is use.'
        internalArgs += '--version'
    }
}
