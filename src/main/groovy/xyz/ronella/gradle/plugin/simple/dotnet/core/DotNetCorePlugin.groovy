package xyz.ronella.gradle.plugin.simple.dotnet.core

import org.gradle.api.Plugin
import org.gradle.api.Project
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetListSDKsTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPackTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetTestTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetVersionTask

/**
 * The Plugin implementation for .Net Core SDK.
 *
 * @author Ron Webb
 * @since 2019-11-29
 */
class DotNetCorePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('simple_dotnet', DotNetCorePluginExtension)
        project.task('dotnetBuild', type: DotNetBuildTask)
        project.task('dotnetClean', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetCleanTask)
        project.task('dotnetInfo', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetInfoTask)
        project.task('dotnetMSBuild', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetMSBuildTask)
        project.task('dotnetNewConsole', type: DotNetNewConsoleTask)
        project.task('dotnetPack', type: DotNetPackTask)
        project.task('dotnetPublish', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPublishTask)
        project.task('dotnetRestore', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRestoreTask)
        project.task('dotnetTask', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetTask)
        project.task('dotnetTest', type: DotNetTestTask)
        project.task('dotnetVersion', type: DotNetVersionTask)
        project.task('dotnetRun', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRunTask)
        project.task('dotnetCreateGlobalJson', type: xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetCreateGlobalJsonTask)
        project.task('dotnetListSDKs', type: DotNetListSDKsTask)
    }
}