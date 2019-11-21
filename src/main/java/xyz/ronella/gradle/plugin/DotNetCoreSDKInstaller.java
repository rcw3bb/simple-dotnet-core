package xyz.ronella.gradle.plugin;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DotNetCoreSDKInstaller {

    private static final String LOCAL_DOTNET_DIR = ".dotnet";

    public String getVersionFromGlobalJson() {
        final String GLOBAL_JSON = "global.json";

        Path jsonPath = Paths.get(".", GLOBAL_JSON).toAbsolutePath();
        File jsonFile = jsonPath.toFile();
        String version = null;

        if (jsonFile.exists()) {
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonPath.toString()));
                JSONObject sdkObject = (JSONObject) jsonObject.get("sdk");
                if (null!=sdkObject) {
                    version = (String) sdkObject.get("version");
                }
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }

        return version;
    }

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
                throw new RuntimeException(ioe);
            }
        }

        return outputScript;
    }

    public void processStreamForOutput(InputStream input, Consumer<List<String>> outputLogic) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(input, Charset.defaultCharset()))) {
            List<String> output = br.lines().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
            outputLogic.accept(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processCommand(List<String> command, Consumer<Process> processLogic) {
        ProcessBuilder pb = new ProcessBuilder(command.toArray(new String[]{}));
        try {
            Process process = pb.start();
            processLogic.accept(process);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getInstalledSDKVersions() {
        final List<String> versions = new ArrayList<>();
        String dotNetCommand = DotNetExecutor.build().getDotNetExe();

        if (null!=dotNetCommand) {
            List<String> command = new ArrayList<>();
            command.add(dotNetCommand);
            command.add("--list-sdks");

            processCommand(command, ___process -> {
                InputStream input = ___process.getInputStream();
                InputStream error = ___process.getErrorStream();

                processStreamForOutput(input, versions::addAll);
                processStreamForOutput(error, ___output -> System.err.println(String.join("\n", ___output)));
            });
        }

        return versions.stream().map(___item -> ___item.split("\\s")[0]).collect(Collectors.toList());
    }

    public void installDotNetSdk() {
        String version = getVersionFromGlobalJson();

        if (null!=DotNetExecutor.build().getDotNetExe() && null==version) {
            System.out.println("global.json with SDK version was not found.");
            return;
        }

        List<String> installedVersions = getInstalledSDKVersions();

        if (null!=installedVersions && installedVersions.contains(version)) {
            System.out.println(String.format(".Net Core SDK v%s was already installed", version));
            return;
        }

        System.out.println("Installing .Net Core SDK");

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

            processCommand(command, ___process -> {
                InputStream input = ___process.getInputStream();
                InputStream error = ___process.getErrorStream();

                processStreamForOutput(input, ___output -> System.out.println(String.join("\n", ___output)));
                processStreamForOutput(error, ___output -> System.err.println(String.join("\n", ___output)));
            });

        }
        else {
            throw new RuntimeException("Installer Script Not Found.");
        }
    }
}
