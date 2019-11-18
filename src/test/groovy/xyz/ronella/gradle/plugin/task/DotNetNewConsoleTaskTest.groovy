package xyz.ronella.gradle.plugin.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertEquals

class DotNetNewConsoleTaskTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testCommand() {
        assertEquals('new', project.tasks.dotnetNewConsole.command)
    }

    @Test
    public void testArgument() {
        assertEquals('console', project.tasks.dotnetNewConsole.args[0] as String)
    }

}
