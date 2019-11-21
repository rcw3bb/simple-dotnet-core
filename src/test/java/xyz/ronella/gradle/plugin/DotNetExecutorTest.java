package xyz.ronella.gradle.plugin;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DotNetExecutorTest {

    @Test
    public void testNoArgument() {
        String command = DotNetExecutor.build()
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe$"));
    }

    @Test
    public void testSingleArgument() {
        String command = DotNetExecutor.build()
                .addArgs("arg1")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1$"));
    }

    @Test
    public void testMultipleArgumentsOnSingleMethod() {
        String command = DotNetExecutor.build()
                .addArgs("arg1", "arg2")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1 arg2$"));
    }

    @Test
    public void testMultipleArgumentsOnMultipleMethod() {
        String command = DotNetExecutor.build()
                .addArgs("arg1")
                .addArgs("arg2")
                .getCommand();
        assertTrue(command.matches(".*dotnet.exe arg1 arg2$"));
    }

    @Test
    public void testMultipleArgumentsWithExecuteBiConsumerCommand() {
        StringBuilder command = new StringBuilder();
        StringBuilder args = new StringBuilder();
        DotNetExecutor.build()
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
        DotNetExecutor.build()
                .addArgs("arg1")
                .addArgs("arg2")
                .execute((___command, ___args) -> {
                    command.append(___command);
                    args.append(String.join(" ", ___args));
                });
        assertEquals("arg1 arg2", args.toString());
    }


}
