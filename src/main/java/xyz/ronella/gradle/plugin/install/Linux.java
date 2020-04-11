package xyz.ronella.gradle.plugin.install;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The class that supplies the command for Linux.
 *
 * @author Ron Webb
 * @since 2020-04-11
 */
public class Linux implements Supplier<List<String>> {

    private Path installer;
    private Path installDir;
    private String version;

    public Linux(Path installer, Path installDir, String version) {
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