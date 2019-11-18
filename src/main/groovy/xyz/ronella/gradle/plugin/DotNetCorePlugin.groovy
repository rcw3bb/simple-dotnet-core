package xyz.ronella.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

import xyz.ronella.gradle.plugin.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.task.DotNetCleanTask
import xyz.ronella.gradle.plugin.task.DotNetCreateGlobalJsonTask
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
        project.task('dotnetBuild', type: DotNetBuildTask,
                description: 'Build a .Net project.')
        project.task('dotnetClean', type: DotNetCleanTask,
                description: 'Clean build outputs of a .NET project.')
        project.task('dotnetInfo', type: DotNetInfoTask,
                description: 'Display .NET Core information.')
        project.task('dotnetMsBuild', type: DotNetMsBuildTask,
                description: 'Run Microsoft Build Engine (MSBuild) command.')
        project.task('dotnetNewConsole', type: DotNetNewConsoleTask,
                description: 'Create a new .NET Console project.')
        project.task('dotnetPack', type: DotNetPackTask,
                description: 'Create a NuGet package.')
        project.task('dotnetPublish', type: DotNetPublishTask,
                description: 'Publish a .NET project for deployment.')
        project.task('dotnetRestore', type: DotNetRestoreTask,
                description: 'Restore dependencies specified in a .NET project.')
        project.task('dotnetTask', type: DotNetTask,
                description: 'Execute any available dotnet core commands.')
        project.task('dotnetTest', type: DotNetTestTask,
                description: 'Run unit tests using the test runner specified in a .NET project.')
        project.task('dotnetVersion', type: DotNetVersionTask,
                description: 'Display .NET Core SDK version is use.')
        project.task('dotnetRun', type: DotNetRunTask,
                description: 'Build and run a .NET project output.')
        project.task('dotnetCreateGlobalJson', type: DotNetCreateGlobalJsonTask,
                description: 'Generates a global.json file.')
    }
}