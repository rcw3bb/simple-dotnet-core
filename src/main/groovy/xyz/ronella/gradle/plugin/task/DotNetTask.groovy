package xyz.ronella.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import xyz.ronella.gradle.plugin.DotNetCorePluginExtension
import xyz.ronella.gradle.plugin.DotNetExecutor

/**
 * The base task implementation.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
class DotNetTask extends DefaultTask {

    protected String[] internalArgs = []
    protected String[] postArguments = []

    @Input
    String command = ''

    @Input
    String[] args = []

    public DotNetTask() {
        group = 'Simple .NET Core'
        description = 'Execute any available .Net Core SDK command.'
    }

    @Input
    public String[] getAllArgs() {
        String[] newArgs = internalArgs + args + postArguments

        return (command.length()>0 || newArgs.length > 0) ? newArgs : ['--help']
    }

    @TaskAction
    def executeCommand() {
        DotNetCorePluginExtension pluginExt = project.extensions.simple_dotnet;
        boolean autoInstall = pluginExt.autoInstall;
        String baseDir = pluginExt.baseDir
        if (autoInstall && null==baseDir) {
            throw new RuntimeException("simple_dotnet.baseDir property is not set.")
        }

        DotNetExecutor.build()
            .addAutoInstall(autoInstall)
            .addBaseDir(baseDir)
            .addArg(command)
            .addArgs(allArgs)
            .execute { String ___command, List<String> ___args ->
                if (null!=___command) {
                    String[] fullCommand = [___command]
                    fullCommand += ___args.toArray()
                    pluginExt.writeln(fullCommand.join(" "))

                    project.exec {
                        executable ___command
                        args ___args
                    }
                }
                else {
                    println("The simple_dotnet.autoInstall property was set to false.")
                    println("Please install a .Net Core SDK.")
                }
            };
    }
}
