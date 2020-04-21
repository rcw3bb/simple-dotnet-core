package xyz.ronella.gradle.plugin;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class DotNetExecutorTest {

    public String getBaseDir() {
        return Paths.get(".").toAbsolutePath().toString();
    }

    public DotNetExecutor.DotNetExecutorBuilder getDotNetExecutorBuilder() {
        return DotNetExecutor.getBuilder()
                .addKnownDotNetExe(Paths.get(".", "src", "test", "resources", "dotnet.exe").toString());
    }

    @Test
    public void testNoArgument() {
        String command = getDotNetExecutorBuilder().getCommand();
        assertTrue(command.matches(".*dotnet.exe$"));
    }

    @Test
    public void testSingleArgument() {
        String command = getDotNetExecutorBuilder()
                .addArgs("arg1")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1$"));
    }

    @Test
    public void testMultipleArgumentsOnSingleMethod() {
        String command = getDotNetExecutorBuilder()
                .addArgs("arg1", "arg2")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1 arg2$"));
    }

    @Test
    public void testMultipleArgumentsOnMultipleMethod() {
        String command = getDotNetExecutorBuilder()
                .addArgs("arg1")
                .addArgs("arg2")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1 arg2$"));
    }

    @Test
    public void testMultipleArgumentsWithExecuteBiConsumerCommand() {
        StringBuilder command = new StringBuilder();
        StringBuilder args = new StringBuilder();
        getDotNetExecutorBuilder()
                .addArgs("arg1")
                .addArgs("arg2")
                .execute((___command, ___args) -> {
                    command.append(___command);
                    args.append(String.join(" ", ___args));
                });
        assertTrue(command.toString().matches(".*dotnet.exe$"));
    }

    @Test
    public void testMultipleArgumentsWithExecuteBiConsumerArgs() {
        StringBuilder command = new StringBuilder();
        StringBuilder args = new StringBuilder();
        getDotNetExecutorBuilder()
                .addArgs("arg1")
                .addArgs("arg2")
                .execute((___command, ___args) -> {
                    command.append(___command);
                    args.append(String.join(" ", ___args));
                });
        assertEquals("arg1 arg2", args.toString());
    }


}
