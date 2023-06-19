package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for building a .Net project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetBuildTask extends DotNetTask {
    DotNetBuildTask() {
        super()
        description= 'Build a .Net project.'
        command = 'build'
    }
}
