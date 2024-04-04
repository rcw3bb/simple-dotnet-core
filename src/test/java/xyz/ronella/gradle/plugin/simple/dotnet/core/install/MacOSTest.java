package xyz.ronella.gradle.plugin.simple.dotnet.core.install;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import xyz.ronella.gradle.plugin.simple.dotnet.core.OSType;

public class MacOSTest {

    @Test
    public void testCommandNoVersion() {
        Supplier<List<String>> supplier = new MacOS(Paths.get(OSType.MacOS.getScriptName()), Paths.get("."), null);
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {"dotnet-install.sh", "--install-dir", "."}
                , command.toArray(new String[] {}));
    }

    @Test
    public void testCommandWithVersion() {
        Supplier<List<String>> supplier = new MacOS(Paths.get(OSType.MacOS.getScriptName()), Paths.get("."), "3.1.100");
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {
                "dotnet-install.sh", "--install-dir", ".", "--version", "3.1.100"}
                , command.toArray(new String[] {}));
    }

}