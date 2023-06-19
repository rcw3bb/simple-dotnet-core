package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

class DotNetNewConsoleTaskTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testCommand() {
        assertEquals('new', project.tasks.dotnetNewConsole.command.get())
    }

    @Test
    public void testArgument() {
        assertEquals('console', project.tasks.dotnetNewConsole.allArgs.get(0) as String)
    }

}
