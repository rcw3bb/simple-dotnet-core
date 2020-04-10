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

        def executor = DotNetExecutor.build()
        executor.addAutoInstall(autoInstall).addBaseDir(baseDir)
        if (command) {
            executor.addArg(command)
        }
        executor.addArgs(allArgs)
            .execute { String ___command, List<String> ___args ->
                if (null!=___command) {
                    String[] fullCommand = [___command]
                    fullCommand += ___args.toArray()

                    pluginExt.writeln("OS: ${System.getProperty('os.name')}")
                    pluginExt.writeln("Command to execute: ${fullCommand.join(' ')}")

                    project.exec {
                        commandLine fullCommand
                    }
                }
                else {
                    println("Either the simple_dotnet.autoInstall property was set to false or you are not in a windows operating system.")
                    println("Please install a .Net Core SDK manually")
                }
            };
    }
}
