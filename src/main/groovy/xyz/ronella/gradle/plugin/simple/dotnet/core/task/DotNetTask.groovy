package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import xyz.ronella.gradle.plugin.simple.dotnet.core.DotNetCorePluginExtension
import xyz.ronella.gradle.plugin.simple.dotnet.core.DotNetExecutor
import xyz.ronella.gradle.plugin.simple.dotnet.core.OSType

/**
 * The base task implementation.
 *
 * @author Ron Webb
 * @since 2019-11-19
 */
abstract class DotNetTask extends DefaultTask {

    protected ListProperty<String> internalArgs
    protected ListProperty<String> postArguments

    @Optional @Input
    abstract Property<String> getCommand()

    @Optional @Input
    abstract ListProperty<String> getArgs()

    DotNetTask() {
        group = 'Simple .NET Core'
        description = 'Execute any available .Net Core SDK command.'

        command.convention('')
        args.convention([])

        final var objects = project.objects

        internalArgs=objects.listProperty(String.class)
        postArguments=objects.listProperty(String.class)
    }

    private void addToNewArgs(final List<String> newArgs, final List<String> list) {
        if (!list.isEmpty()) {
            newArgs.addAll(list)
        }
    }

    @Input
    List<String> getAllArgs() {
        final var newArgs = new ArrayList<String>()
        final var cmd = command.get()

        addToNewArgs(newArgs, internalArgs.get())
        addToNewArgs(newArgs, args.get())
        addToNewArgs(newArgs, postArguments.get())

        if (cmd.length()==0 && newArgs.isEmpty()) {
            newArgs.add('--help')
        }

        return newArgs
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
                switch (osType) {
                    case OSType.Windows:
                        String[] execs = knownDotNet.split("\r\n")
                        knownDotNet = execs.first()
                        break
                }
                return knownDotNet
            }
        }

        return null
    }

    @TaskAction
    def executeCommand() {
        DotNetCorePluginExtension pluginExt = project.extensions.simple_dotnet;
        boolean autoInstall = pluginExt.autoInstall.getOrElse(true)
        String baseDir = pluginExt.baseDir.getOrNull()

        if (autoInstall && null==baseDir) {
            baseDir = project.projectDir.absolutePath
            println("Using ${baseDir} as baseDir.")
        }

        def knownDotNet = detectDotNetExec()
        def executor = DotNetExecutor.getBuilder()

        executor.addAutoInstall(autoInstall)
                .addBaseDir(baseDir)

        if (knownDotNet!=null) {
            pluginExt.writeln("Known dotnet location in PATH: ${knownDotNet}")
            executor.addKnownDotNetExe(knownDotNet)
        }

        if (command.isPresent()) {
            final var cmd = command.get().trim()
            if (cmd.length()>0) {
                executor.addArg(cmd)
            }
        }
        executor.addArgs(allArgs.toArray(new String[] {}))
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
