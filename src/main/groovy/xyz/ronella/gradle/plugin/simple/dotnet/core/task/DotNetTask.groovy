package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecOperations
import xyz.ronella.gradle.plugin.simple.dotnet.core.DotNetExecutor
import xyz.ronella.gradle.plugin.simple.dotnet.core.OSType

import javax.inject.Inject

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

    /**
     * The autoInstall value sourced (by convention) from the simple_dotnet extension.
     * Kept as a task property (instead of reaching into the extension at execution time)
     * so that this task remains compatible with the Gradle configuration cache.
     */
    @Internal
    abstract Property<Boolean> getAutoInstall()

    /**
     * The baseDir value sourced (by convention) from the simple_dotnet extension.
     */
    @Internal
    abstract Property<String> getBaseDir()

    /**
     * The verbose value sourced (by convention) from the simple_dotnet extension.
     */
    @Internal
    abstract Property<Boolean> getVerbose()

    @Inject
    protected abstract ObjectFactory getObjectFactory()

    @Inject
    protected abstract ExecOperations getExecOperations()

    @Inject
    protected abstract ProjectLayout getProjectLayout()

    DotNetTask() {
        group = 'Simple .NET Core'
        description = 'Execute any available .Net Core SDK command.'

        command.convention('')
        args.convention([])

        internalArgs=objectFactory.listProperty(String.class)
        postArguments=objectFactory.listProperty(String.class)
    }

    /**
     * Utility method for printing text on the console based on the verbose property.
     * @param text The value to be printed.
     */
    protected void writeln(String text) {
        if (verbose.getOrElse(false)) {
            println(text)
        }
    }

    private void addToNewArgs(final List<String> newArgs, final List<String> list) {
        if (!list.isEmpty()) {
            // Coerce each element to a genuine java.lang.String. Groovy allows GString (and other
            // CharSequence) instances to be stored in a List<String> at runtime despite the generic
            // type, since generics are erased and Gradle's ListProperty does not validate/convert
            // element types on assignment. Without this coercion, allArgs.toArray(new String[]{})
            // below throws an ArrayStoreException ("arraycopy: element type mismatch") as soon as a
            // consumer's args list contains an interpolated GString (e.g. "-property:Foo=${bar}"),
            // which is an idiomatic and common pattern in Gradle build scripts.
            list.each { newArgs.add(it.toString()) }
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
        def osType = DotNetExecutor.OS_TYPE
        def dotnetExec = DotNetExecutor.DOTNET_EXE
        String cmd = null
        switch (osType) {
            case OSType.Windows:
                cmd="where"
                break;
            case [OSType.Linux, OSType.MacOS]:
                cmd="which"
                break;
        }

        if (cmd) {
            def stdOutput = new ByteArrayOutputStream()
            def errOutput = new ByteArrayOutputStream()

            execOperations.exec {
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
        boolean isAutoInstall = autoInstall.getOrElse(true)
        String theBaseDir = baseDir.getOrNull()

        if (isAutoInstall && null==theBaseDir) {
            theBaseDir = projectLayout.projectDirectory.asFile.absolutePath
            println("Using ${theBaseDir} as baseDir.")
        }

        def knownDotNet = detectDotNetExec()
        def executor = DotNetExecutor.getBuilder()

        executor.addAutoInstall(isAutoInstall)
                .addBaseDir(theBaseDir)

        if (knownDotNet!=null) {
            writeln("Known dotnet location in PATH: ${knownDotNet}")
            executor.addKnownDotNetExe(knownDotNet)
        }

        if (command.isPresent()) {
            // Coerce to a genuine String for the same reason as addToNewArgs() above: command may
            // have been assigned a GString (e.g. command = "${foo}"), and downstream consumers of
            // this value (e.g. DotNetExecutor) assume a plain java.lang.String.
            final var cmd = command.get().toString().trim()
            if (cmd.length()>0) {
                executor.addArg(cmd)
            }
        }
        executor.addArgs(allArgs.toArray(new String[] {}))
            .execute { String ___command, List<String> ___args ->
                if (null!=___command) {
                    String[] fullCommand = [___command]
                    fullCommand += ___args.toArray()

                    writeln("OS: ${System.getProperty('os.name')}")
                    writeln("Command to execute: ${fullCommand.join(' ')}")

                    execOperations.exec {
                        commandLine fullCommand
                    }
                }
                else {
                    println("Either the simple_dotnet.autoInstall property was set to false or you are not in a windows, linux or macos operating system.")
                    println("Please install a .Net Core SDK manually and must be accessible via PATH environment variable")
                }
            };
    }
}



