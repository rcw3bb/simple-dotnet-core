package xyz.ronella.gradle.plugin.simple.dotnet.core.install;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The class that supplies the command for MacOS.
 *
 * @author Martin Rossland
 * @since 2024-05-01
 */
public class MacOS implements Supplier<List<String>> {

    private Path installer;
    private Path installDir;
    private String version;

    public MacOS(Path installer, Path installDir, String version) {
        this.installer = installer;
        this.installDir = installDir;
        this.version = version;
    }

    @Override
    public List<String> get() {
        List<String> command = new ArrayList<>();

        command.add(installer.toString());
        command.add("--install-dir");
        command.add(installDir.toString());

        if (null != version) {
            command.add("--version");
            command.add(version);
        }

        return command;
    }
}