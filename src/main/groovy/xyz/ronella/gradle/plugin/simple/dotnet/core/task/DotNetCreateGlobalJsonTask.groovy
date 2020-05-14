package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for generating a global.json file.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetCreateGlobalJsonTask extends DotNetTask {
    public DotNetCreateGlobalJsonTask() {
        super()
        description = 'Generate a global.json file.'
        command = 'new'
        internalArgs += 'globaljson'
    }
}