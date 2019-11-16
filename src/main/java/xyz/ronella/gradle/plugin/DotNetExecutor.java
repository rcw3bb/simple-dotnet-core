package xyz.ronella.gradle.plugin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

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

    private String getDotNetExeByProgramFile() {
        Path programFile = Paths.get("C:", "Program Files", "dotnet", DOTNET_EXE);
        if (programFile.toFile().exists()) {
            return programFile.toString();
        }
        return null;
    }

    private String getDotNetExe() {

        String byEnvVar=getDotNetExeByEnvVar();
        if (null!=byEnvVar)  {
            return byEnvVar;
        }

        String byProgramFile = getDotNetExeByProgramFile();
        if (null!=byProgramFile) {
            return byProgramFile;
        }

        return DOTNET_EXE;
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

        public String getCommand() {
            DotNetExecutor executor = new DotNetExecutor();
            if (args.size()>0) {
                executor.setArgs(args);
            }
            return executor.getCommand();
        }

        public void execute(Consumer<String> logic) {
            logic.accept(getCommand());
        }
    }

    public static DotNetExecutorBuilder build() {
        return new DotNetExecutorBuilder();
    }
}
