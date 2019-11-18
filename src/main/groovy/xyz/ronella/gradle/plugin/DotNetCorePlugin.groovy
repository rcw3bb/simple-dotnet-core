package xyz.ronella.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import xyz.ronella.gradle.plugin.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.task.DotNetCleanTask
import xyz.ronella.gradle.plugin.task.DotNetInfoTask
import xyz.ronella.gradle.plugin.task.DotNetMsBuildTask
import xyz.ronella.gradle.plugin.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.task.DotNetPackTask
import xyz.ronella.gradle.plugin.task.DotNetPublishTask
import xyz.ronella.gradle.plugin.task.DotNetRestoreTask
import xyz.ronella.gradle.plugin.task.DotNetRunTask
import xyz.ronella.gradle.plugin.task.DotNetTask
import xyz.ronella.gradle.plugin.task.DotNetTestTask
import xyz.ronella.gradle.plugin.task.DotNetVersionTask

class DotNetCorePlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.task('dotnetBuild', type: DotNetBuildTask)
        project.task('dotnetClean', type: DotNetCleanTask)
        project.task('dotnetInfo', type: DotNetInfoTask)
        project.task('dotnetMsBuild', type: DotNetMsBuildTask)
        project.task('dotnetNewConsole', type: DotNetNewConsoleTask)
        project.task('dotnetPack', type: DotNetPackTask)
        project.task('dotnetPublish', type: DotNetPublishTask)
        project.task('dotnetRestore', type: DotNetRestoreTask)
        project.task('dotnetTask', type: DotNetTask)
        project.task('dotnetTest', type: DotNetTestTask)
        project.task('dotnetVersion', type: DotNetVersionTask)
        project.task('dotnetRun', type: DotNetRunTask)
    }
}