package xyz.ronella.gradle.plugin.simple.dotnet.core.task

/**
 * The task for generating a global.json file.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetCreateGlobalJsonTask extends DotNetTask {
    DotNetCreateGlobalJsonTask() {
        super()
        description = 'Generate a global.json file.'
        command = 'new'
        internalArgs.add('globaljson')
    }
}