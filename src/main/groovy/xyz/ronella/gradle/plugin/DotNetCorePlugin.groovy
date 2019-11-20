package xyz.ronella.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import xyz.ronella.gradle.plugin.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.task.DotNetCleanTask
import xyz.ronella.gradle.plugin.task.DotNetCreateGlobalJsonTask
import xyz.ronella.gradle.plugin.task.DotNetInfoTask
import xyz.ronella.gradle.plugin.task.DotNetListSDKsTask
import xyz.ronella.gradle.plugin.task.DotNetMSBuildTask
import xyz.ronella.gradle.plugin.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.task.DotNetPackTask
import xyz.ronella.gradle.plugin.task.DotNetPublishTask
import xyz.ronella.gradle.plugin.task.DotNetRestoreTask
import xyz.ronella.gradle.plugin.task.DotNetRunTask
import xyz.ronella.gradle.plugin.task.DotNetTask
import xyz.ronella.gradle.plugin.task.DotNetTestTask
import xyz.ronella.gradle.plugin.task.DotNetVersionTask

/**
 * The Plugin implementation for .Net Core SDK.
 *
 * @author Ron Webb
 * @since 2019-11-29
 */
class DotNetCorePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
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