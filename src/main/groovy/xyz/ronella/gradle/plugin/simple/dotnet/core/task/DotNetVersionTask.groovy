package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for knowing the current SDK version being used.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetVersionTask extends DotNetTask {
    DotNetVersionTask() {
        super()
        description = 'Display .NET Core SDK version in use.'
        internalArgs.add('--version')
    }
}
