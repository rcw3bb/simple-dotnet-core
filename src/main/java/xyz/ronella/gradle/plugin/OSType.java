package xyz.ronella.gradle.plugin;

/**
 * The enumerator that identifies the OSType.
 *
 * @author Ron Webb
 * @since 2020-04-11
 */
public enum OSType {
    Windows("dotnet.exe", "dotnet-install.ps1"),
    Linux("dotnet", "dotnet-install.sh"),
    Unknown(null, null);

    private String scriptName;
    private String executable;

    OSType(String executable, String scriptName) {
        this.executable = executable;
        this.scriptName = scriptName;
    }

    public String getScriptName() {
        return scriptName;
    }

    public String getExecutable() {
        return executable;
    }

    public boolean canInstall() {
        return scriptName!=null;
    }

    public static OSType identify() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OSType.Windows;
        }
        else if (osName.contains("nux")) {
            return OSType.Linux;
        }
        return OSType.Unknown;
    }
}
