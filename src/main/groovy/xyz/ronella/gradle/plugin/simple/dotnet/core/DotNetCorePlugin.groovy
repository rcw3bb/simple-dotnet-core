package xyz.ronella.gradle.plugin.simple.dotnet.core

import org.gradle.api.Plugin
import org.gradle.api.Project
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetCleanTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetCreateGlobalJsonTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetInfoTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetListSDKsTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetMSBuildTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPackTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPublishTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRestoreTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRunTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetTask
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
        project.task('dotnetClean', type: DotNetCleanTask)
        project.task('dotnetInfo', type: DotNetInfoTask)
        project.task('dotnetMSBuild', type: DotNetMSBuildTask)
        project.task('dotnetNewConsole', type: DotNetNewConsoleTask)
        project.task('dotnetPack', type: DotNetPackTask)
        project.task('dotnetPublish', type: DotNetPublishTask)
        project.task('dotnetRestore', type: DotNetRestoreTask)
        project.task('dotnetTask', type: DotNetTask)
        project.task('dotnetTest', type: DotNetTestTask)
        project.task('dotnetVersion', type: DotNetVersionTask)
        project.task('dotnetRun', type: DotNetRunTask)
        project.task('dotnetCreateGlobalJson', type: DotNetCreateGlobalJsonTask)
        project.task('dotnetListSDKs', type: DotNetListSDKsTask)
    }
}