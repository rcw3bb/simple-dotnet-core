package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import static org.junit.jupiter.api.Assertions.*;

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DotNetCreateGlobalJsonTaskTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testCommand() {
        assertEquals('new', project.tasks.dotnetCreateGlobalJson.command.get())
    }

    @Test
    public void testArgument() {
        assertEquals('globaljson', project.tasks.dotnetCreateGlobalJson.allArgs[0] as String)
    }

}
