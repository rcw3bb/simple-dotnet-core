package xyz.ronella.gradle.plugin.simple.dotnet.core.install;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import xyz.ronella.gradle.plugin.simple.dotnet.core.OSType;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

public class WindowsTest {

    @Test
    public void testCommandNoVersion() {
        Supplier<List<String>> supplier = new Windows(Paths.get(OSType.Windows.getScriptName()), Paths.get("."), null);
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {
                        "PowerShell.Exe", "-executionpolicy", "bypass", "-File",
                        "dotnet-install.ps1", "-InstallDir", "."}
                , command.toArray(new String[] {}));
    }

    @Test
    public void testCommandWithVersion() {
        Supplier<List<String>> supplier = new Windows(Paths.get(OSType.Windows.getScriptName()), Paths.get("."), "3.1.100");
        List<String> command = supplier.get();
        assertArrayEquals(new String[] {
                "PowerShell.Exe", "-executionpolicy", "bypass", "-File",
                        "dotnet-install.ps1", "-InstallDir", ".", "-Version", "3.1.100"}
            , command.toArray(new String[] {}));
    }

}
