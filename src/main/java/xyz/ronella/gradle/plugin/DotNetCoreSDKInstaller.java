package xyz.ronella.gradle.plugin;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DotNetCoreSDKInstaller {

    private static final String LOCAL_DOTNET_DIR = ".dotnet";

    public Path getScriptPath(String script) {
        final String DEFAULT_JOIN_DELIMITER = "/";
        final String SCRIPTS_DIR = "scripts";

        String internalScript = String.join(DEFAULT_JOIN_DELIMITER, SCRIPTS_DIR, script);
        Path pathScript = Paths.get(".", LOCAL_DOTNET_DIR, SCRIPTS_DIR).toAbsolutePath();
        File fileScript = pathScript.toFile();
        Path outputScript = Paths.get(fileScript.toString(), script);

        if (!outputScript.toFile().exists()) {
            fileScript.mkdirs();
            try (InputStream isStream = this.getClass().getClassLoader().getResourceAsStream(internalScript)) {
                Files.copy(isStream, outputScript);
            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        return outputScript;
    }

    public void installDotNetSdk() {
        installDotNetSdk(null);
    }

    public void installDotNetSdk(String version) {
        Path pathInstaller = getScriptPath("dotnet-install.ps1");
        final String POWER_SHELL = "PowerShell.Exe";
        final String INSTALL_DIR = "dotnet";

        Path pathInstallDir = Paths.get(".", LOCAL_DOTNET_DIR, INSTALL_DIR).toAbsolutePath();

        if (pathInstaller.toFile().exists()) {

            List<String> command = new ArrayList<>();
            command.add(POWER_SHELL);
            command.add("-executionpolicy");
            command.add("bypass");
            command.add("-File");
            command.add(pathInstaller.toString());
            command.add("-InstallDir");
            command.add(pathInstallDir.toString());

            if (null!=version) {
                command.add("-Version");
                command.add(version);
            }

            ProcessBuilder pb = new ProcessBuilder(command.toArray(new String[] {}));
            try {
                Process process = pb.start();

                InputStream input = process.getInputStream();
                InputStream error = process.getErrorStream();

                try (BufferedReader br = new BufferedReader(new InputStreamReader(input, Charset.defaultCharset()))) {
                    List<String> output = br.lines().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                    System.out.println(String.join("\n", output));
                };

                try (BufferedReader br = new BufferedReader(new InputStreamReader(error, Charset.defaultCharset()))) {
                    List<String> output = br.lines().collect(Collectors.toList());
                    System.err.println(String.join("\n", output));
                };
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new RuntimeException("Installer Script Not Found.");
        }
    }

}
