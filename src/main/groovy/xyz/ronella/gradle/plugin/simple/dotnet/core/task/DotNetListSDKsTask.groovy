package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for knowing the installed sdk visible form dotnet.exe command.
 *
 * @author Ron Webb
 * @since 2019-11-20
 */
class DotNetListSDKsTask extends DotNetTask {
    public DotNetListSDKsTask() {
        super()
        description = 'Display the installed SDKs.'
        internalArgs += '--list-sdks'
    }
}