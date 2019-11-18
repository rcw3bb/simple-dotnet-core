package xyz.ronella.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import xyz.ronella.gradle.plugin.DotNetExecutor

class DotNetTask extends DefaultTask {
    String command = ''
    String[] args = []

    @TaskAction
    def executeCommand() {
        DotNetExecutor.build()
            .addArg(command)
            .addArgs(args)
            .execute { String ___command, List<String> ___args ->
                project.exec {
                    executable ___command
                    args ___args
                }
            };
    }
}
