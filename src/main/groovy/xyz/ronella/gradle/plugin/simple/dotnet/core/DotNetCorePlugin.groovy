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

        project.tasks.withType(DotNetTask).configureEach { task ->
            task.autoInstall.convention(extension.autoInstall)
            task.baseDir.convention(extension.baseDir)
            task.verbose.convention(extension.verbose)
        }

        project.tasks.register('dotnetBuild', DotNetBuildTask)
        project.tasks.register('dotnetClean', DotNetCleanTask)
        project.tasks.register('dotnetInfo', DotNetInfoTask)
        project.tasks.register('dotnetMSBuild', DotNetMSBuildTask)
        project.tasks.register('dotnetNewConsole', DotNetNewConsoleTask)
        project.tasks.register('dotnetPack', DotNetPackTask)
        project.tasks.register('dotnetPublish', DotNetPublishTask)
        project.tasks.register('dotnetRestore', DotNetRestoreTask)
        project.tasks.register('dotnetTask', DotNetTask)
        project.tasks.register('dotnetTest', DotNetTestTask)
        project.tasks.register('dotnetVersion', DotNetVersionTask)
        project.tasks.register('dotnetRun', DotNetRunTask)
        project.tasks.register('dotnetCreateGlobalJson', DotNetCreateGlobalJsonTask)
        project.tasks.register('dotnetListSDKs', DotNetListSDKsTask)
    }
}