package xyz.ronella.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import xyz.ronella.gradle.plugin.DotNetExecutor

/**
 * The base task implementation.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetTask extends DefaultTask {

    @Input
    String command = ''

    protected String[] internalArgs = []

    @Input
    String[] args = []

    public DotNetTask() {
        group = 'Simple .NET Core'
        description = 'Execute any available .Net Core SDK command.'
    }

    @Input
    public String[] getAllArgs() {
        return internalArgs+=args
    }

    @TaskAction
    def executeCommand() {
        DotNetExecutor.build()
            .addArg(command)
            .addArgs(allArgs)
            .execute { String ___command, List<String> ___args ->
                project.exec {
                    executable ___command
                    args ___args
                }
            };
    }
}
