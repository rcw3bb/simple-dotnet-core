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
                group: 'Simple .NET Core',
                description: 'Build a .Net project.')
        project.task('dotnetClean', type: DotNetCleanTask,
                group: 'Simple .NET Core',
                description: 'Clean build outputs of a .NET project.')
        project.task('dotnetInfo', type: DotNetInfoTask,
                group: 'Simple .NET Core',
                description: 'Display .NET Core information.')
        project.task('dotnetMsBuild', type: DotNetMsBuildTask,
                group: 'Simple .NET Core',
                description: 'Run Microsoft Build Engine (MSBuild) command.')
        project.task('dotnetNewConsole', type: DotNetNewConsoleTask,
                group: 'Simple .NET Core',
                description: 'Create a new .NET Console project.')
        project.task('dotnetPack', type: DotNetPackTask,
                group: 'Simple .NET Core',
                description: 'Create a NuGet package.')
        project.task('dotnetPublish', type: DotNetPublishTask,
                group: 'Simple .NET Core',
                description: 'Publish a .NET project for deployment.')
        project.task('dotnetRestore', type: DotNetRestoreTask,
                group: 'Simple .NET Core',
                description: 'Restore dependencies specified in a .NET project.')
        project.task('dotnetTask', type: DotNetTask,
                group: 'Simple .NET Core',
                description: 'Execute any available dotnet core commands.')
        project.task('dotnetTest', type: DotNetTestTask,
                group: 'Simple .NET Core',
                description: 'Run unit tests using the test runner specified in a .NET project.')
        project.task('dotnetVersion', type: DotNetVersionTask,
                group: 'Simple .NET Core',
                description: 'Display .NET Core SDK version is use.')
        project.task('dotnetRun', type: DotNetRunTask,
                group: 'Simple .NET Core',
                description: 'Build and run a .NET project output.')
        project.task('dotnetCreateGlobalJson', type: DotNetCreateGlobalJsonTask,
                group: 'Simple .NET Core',
                description: 'Generate a global.json file.')
    }
}