package xyz.ronella.gradle.plugin.simple.dotnet.core.install;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The class that supplies the command for Windows.
 *
 * @author Ron Webb
 * @since 2020-04-11
 */
public class Windows implements Supplier<List<String>> {

    private Path installer;
    private Path installDir;
    private String version;

    public Windows(Path installer, Path installDir, String version) {
        this.installer = installer;
        this.installDir = installDir;
        this.version = version;
    }

    @Override
    public List<String> get() {
        final String POWER_SHELL = "PowerShell.Exe";
        List<String> command = new ArrayList<>();
        command.add(POWER_SHELL);
        command.add("-executionpolicy");
        command.add("bypass");
        command.add("-File");
        command.add(installer.toString());
        command.add("-InstallDir");
        command.add(installDir.toString());

        if (null != version) {
            command.add("-Version");
            command.add(version);
        }

        return command;
    }
}
