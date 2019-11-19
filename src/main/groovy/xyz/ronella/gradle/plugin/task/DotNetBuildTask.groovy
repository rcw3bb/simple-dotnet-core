package xyz.ronella.gradle.plugin.task

/**
 * The task for building a .Net project.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetBuildTask extends DotNetTask {
    public DotNetBuildTask() {
        super()
        description= 'Build a .Net project.'
        command = 'build'
    }
}
