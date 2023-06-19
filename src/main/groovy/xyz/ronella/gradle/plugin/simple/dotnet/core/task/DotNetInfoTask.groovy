package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for knowing the .Net Core information on your machine.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetInfoTask extends DotNetTask {
    DotNetInfoTask() {
        super()
        description = 'Display .NET Core information.'
        internalArgs.add('--info')
    }
}