package xyz.ronella.gradle.plugin.simple.dotnet.core.install;

import org.junit.jupiter.api.Test;
import xyz.ronella.gradle.plugin.simple.dotnet.core.OSType;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

public class LinuxTest {

    @Test
    public void testCommandNoVersion() {
        Supplier<List<String>> supplier = new Linux(Paths.get(OSType.Linux.getScriptName()), Paths.get("."), null);
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {"dotnet-install.sh", "--install-dir", "."}
                , command.toArray(new String[] {}));
    }

    @Test
    public void testCommandWithVersion() {
        Supplier<List<String>> supplier = new Linux(Paths.get(OSType.Linux.getScriptName()), Paths.get("."), "3.1.100");
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {
                "dotnet-install.sh", "--install-dir", ".", "--version", "3.1.100"}
                , command.toArray(new String[] {}));
    }

}