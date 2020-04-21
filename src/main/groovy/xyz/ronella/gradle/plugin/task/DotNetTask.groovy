package xyz.ronella.gradle.plugin.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import xyz.ronella.gradle.plugin.DotNetCorePluginExtension
import xyz.ronella.gradle.plugin.DotNetExecutor
import xyz.ronella.gradle.plugin.OSType

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

    private String detectDotNetExec() {
        DotNetCorePluginExtension pluginExt = project.extensions.simple_dotnet;

        def osType = DotNetExecutor.OS_TYPE
        def dotnetExec = DotNetExecutor.DOTNET_EXE
        String cmd = null
        switch (osType) {
            case OSType.Windows:
                cmd="where"
                break;
            case OSType.Linux:
                cmd="which"
                break;
        }

        if (cmd) {
            def stdOutput = new ByteArrayOutputStream()
            def errOutput = new ByteArrayOutputStream()

            project.exec {
                executable cmd
                args dotnetExec
                standardOutput = stdOutput
                errorOutput = errOutput
                ignoreExitValue = true
            }

            def error = errOutput.toString()

            if (error.size()>0) {
                println("detectDotNetExec Error: ${error}")
            }

            def knownDotNet = stdOutput.toString().trim()

            if (knownDotNet.size()>0) {
                return knownDotNet
            }
        }

        return null
    }

    @TaskAction
    def executeCommand() {
        DotNetCorePluginExtension pluginExt = project.extensions.simple_dotnet;
        boolean autoInstall = pluginExt.autoInstall;
        String baseDir = pluginExt.baseDir

        if (autoInstall && null==baseDir) {
            throw new RuntimeException("simple_dotnet.baseDir property is not set.")
        }

        def knownDotNet = detectDotNetExec()
        def executor = DotNetExecutor.getBuilder()

        executor.addAutoInstall(autoInstall)
                .addBaseDir(baseDir)

        if (knownDotNet!=null) {
            pluginExt.writeln("Known dotnet location in PATH: ${knownDotNet}")
            executor.addKnownDotNetExe(knownDotNet)
        }

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
                    println("Either the simple_dotnet.autoInstall property was set to false or you are not in a windows or linux operating system.")
                    println("Please install a .Net Core SDK manually and must be accessible via PATH environment variable")
                }
            };
    }
}
