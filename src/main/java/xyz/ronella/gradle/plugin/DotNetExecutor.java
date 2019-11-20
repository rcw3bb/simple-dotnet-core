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

    private String getDotNetExeByEnvVar() {
        String dotnetHome=System.getenv("DOTNET_CORE_HOME");
        if (null!=dotnetHome)  {
            return Paths.get(dotnetHome, DOTNET_EXE).toString();
        }
        return null;
    }

    private String getDotNetExeByProjectDir() {
        String projectDir=Paths.get(".", ".dotnet").toAbsolutePath().toString();
        Path programFile = Paths.get(projectDir, "dotnet", DOTNET_EXE);
        if (programFile.toFile().exists()) {
            return programFile.toString();
        }
        return null;
    }

    private String getDotNetExeByLocalAppData() {
        String localAppData=System.getenv("LOCALAPPDATA");
        Path programFile = Paths.get(localAppData, "Microsoft", "dotnet", DOTNET_EXE);
        if (programFile.toFile().exists()) {
            return programFile.toString();
        }
        return null;
    }

    private String getDotNetExeByProgramFile() {
        Path programFile = Paths.get("C:", "Program Files", "dotnet", DOTNET_EXE);
        if (programFile.toFile().exists()) {
            return programFile.toString();
        }
        return null;
    }

    private String getDotNetExe() {

        List<Supplier<String>> finder = Arrays.asList(
                this::getDotNetExeByEnvVar,
                this::getDotNetExeByProjectDir,
                this::getDotNetExeByLocalAppData,
                this::getDotNetExeByProgramFile,
                () -> DOTNET_EXE
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
        StringBuilder command = new StringBuilder(getDotNetExe());
        if (null!=args && args.size() > 0) {
            command.append(" ").append(String.join(" ",args));
        }
        return command.toString();
    }

    public static class DotNetExecutorBuilder {
        private DotNetExecutorBuilder() {}

        private List<String> args = new ArrayList<String>();

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

        public String getDotNetExe() {
            DotNetExecutor executor = new DotNetExecutor();
            return executor.getDotNetExe();
        }

        public String getCommand() {
            DotNetExecutor executor = new DotNetExecutor();
            if (args.size()>0) {
                executor.setArgs(args);
            }
            return executor.getCommand();
        }

        public void execute(BiConsumer<String, List<String>> logic) {
            logic.accept(getDotNetExe(), args);
        }
    }

    public static DotNetExecutorBuilder build() {
        return new DotNetExecutorBuilder();
    }
}
