package xyz.ronella.gradle.plugin;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DotNetExecutorTest {

    @Test
    public void testNoArgument() {
        String command = DotNetExecutor.build()
                .getCommand();
        System.out.println(System.getenv("DOTNET_CORE_HOME"));
        System.out.println(command);
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
    public void testMultipleArgumentsWithExecute() {
        StringBuilder executeCommand = new StringBuilder();
        DotNetExecutor.build()
                .addArgs("arg1")
                .addArgs("arg2")
                .execute(executeCommand::append);
       assertTrue(executeCommand.toString().matches(".*dotnet.exe arg1 arg2$"));
    }

}
