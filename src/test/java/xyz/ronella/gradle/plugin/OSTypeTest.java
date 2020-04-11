package xyz.ronella.gradle.plugin;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OSTypeTest {

    @Test
    public void testWindowsScript() {
        assertEquals("dotnet-install.ps1", OSType.Windows.getScriptName());
    }

    @Test
    public void testLinuxScript() {
        assertEquals("dotnet-install.sh", OSType.Linux.getScriptName());
    }

    @Test
    public void testUknownScript() {
        assertNull(OSType.Unknown.getScriptName());
    }

    @Test
    public void testWindowsExecutable() {
        assertEquals("dotnet.exe", OSType.Windows.getExecutable());
    }

    @Test
    public void testLinuxExecutable() {
        assertEquals("dotnet", OSType.Linux.getExecutable());
    }

    @Test
    public void testUknownExecutable() {
        assertNull(OSType.Unknown.getExecutable());
    }

    @Test
    public void testWindowsCanInstall() {
        assertTrue(OSType.Windows.canInstall());
    }

    @Test
    public void testLinuxCanInstall() {
        assertTrue(OSType.Linux.canInstall());
    }

    @Test
    public void testUknownCanInstall() {
        assertFalse(OSType.Unknown.canInstall());
    }

}
