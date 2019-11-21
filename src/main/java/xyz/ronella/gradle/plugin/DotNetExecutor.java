package xyz.ronella.gradle.plugin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

/**
 * The executor of the dotnet commands.
 *
 * @author Ron Webb
 * @since 2019-11-29
 */
public class DotNetExecutor {

    private final static String DOTNET_EXE = "dotnet.exe";

    private List<String> args;

    private DotNetExecutor() {}

    private void setArgs(List<String> args) {
        this.args=args;
    }

    private String getProgramFile(Path programFile) {
        if (programFile.toFile().exists())  {
            return programFile.toString();
        }
        return null;
    }

    private String getDotNetExeByEnvVar() {
        String dotnetHome=System.getenv("DOTNET_CORE_HOME");
        Path programFile = Paths.get(dotnetHome, DOTNET_EXE);
        return getProgramFile(programFile);
    }

    private String getDotNetExeByProjectDir() {
        String projectDir=Paths.get(".", ".dotnet").toAbsolutePath().toString();
        Path programFile = Paths.get(projectDir, "dotnet", DOTNET_EXE);
        return getProgramFile(programFile);
    }

    private String getDotNetExeByLocalAppData() {
        String localAppData=System.getenv("LOCALAPPDATA");
        Path programFile = Paths.get(localAppData, "Microsoft", "dotnet", DOTNET_EXE);
        return getProgramFile(programFile);
    }

    private String getDotNetExeByProgramFile() {
        Path programFile = Paths.get("C:", "Program Files", "dotnet", DOTNET_EXE);
        return getProgramFile(programFile);
    }

    private String getDotNetExe() {
        List<Supplier<String>> finder = Arrays.asList(
                this::getDotNetExeByEnvVar,
                this::getDotNetExeByProjectDir,
                this::getDotNetExeByLocalAppData,
                this::getDotNetExeByProgramFile
        );

        String command = null;

        for (Supplier<String> resolver : finder) {
            command = resolver.get();
            if (null!=command) {
                break;
            }
        }

        return command;
    }

    private String getCommand() {
        String dotNetExe = getDotNetExe();

        if (null==dotNetExe) {
            return null;
        }

        StringBuilder command = new StringBuilder(dotNetExe);
        if (null!=args && args.size() > 0) {
            command.append(" ").append(String.join(" ",args));
        }
        return command.toString();
    }

    public static class DotNetExecutorBuilder {
        private DotNetExecutorBuilder() {}

        private List<String> args = new ArrayList<String>();
        private String baseDir;
        private boolean autoInstall;

        public DotNetExecutorBuilder addBaseDir(String baseDir) {
            this.baseDir = baseDir;
            return this;
        }

        public DotNetExecutorBuilder addAutoInstall(boolean autoInstall) {
            this.autoInstall = autoInstall;
            return this;
        }

        public DotNetExecutorBuilder addArg(String arg) {
            if (null!=arg) {
                args.add(arg);
            }
            return this;
        }

        public DotNetExecutorBuilder addArgs(String ... args) {
            if (null!=args) {
                this.args.addAll(Arrays.asList(args));
            }
            return this;
        }

        private DotNetExecutor getDotNetExecutor() {
            return new DotNetExecutor();
        }

        public String getDotNetExe() {
            DotNetExecutor executor = getDotNetExecutor();
            return executor.getDotNetExe();
        }

        public String getCommand() {
            DotNetExecutor executor = getDotNetExecutor();
            if (args.size()>0) {
                executor.setArgs(args);
            }
            return executor.getCommand();
        }

        public void execute(BiConsumer<String, List<String>> logic) {
            String dotnetExe = getDotNetExe();

            if (autoInstall) {
                DotNetCoreSDKInstaller installer = new DotNetCoreSDKInstaller();
                String globalVersion = installer.getVersionFromGlobalJson(baseDir);

                if (null == dotnetExe || (null != globalVersion)) {
                    installer.installDotNetSdk(globalVersion);
                    dotnetExe = getDotNetExe();
                }
            }

            logic.accept(dotnetExe, args);
        }
    }

    public static DotNetExecutorBuilder build() {
        return new DotNetExecutorBuilder();
    }
}
