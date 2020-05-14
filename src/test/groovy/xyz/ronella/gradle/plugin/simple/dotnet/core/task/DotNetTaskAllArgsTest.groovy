package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class DotNetTaskAllArgsTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void hasCommandWithNoArgs() {
        DotNetTask netTask = project.tasks.dotnetTask
        netTask.command = 'command'
        assertEquals(0, netTask.allArgs.length)
    }

    @Test
    public void hasNoCommandWithArgs() {
        DotNetTask netTask = project.tasks.dotnetTask
        netTask.args = ['arg1']
        assertEquals('arg1', netTask.allArgs[0])
    }

    @Test
    public void hasCommandWithArgs() {
        DotNetTask netTask = project.tasks.dotnetTask
        netTask.command = 'command'
        netTask.args = ['arg1']
        assertEquals('arg1', netTask.allArgs.join(' '))
    }

    @Test
    public void hasNoCommandWithNoArgs() {
        DotNetTask netTask = project.tasks.dotnetTask
        assertEquals('--help', netTask.allArgs[0])
    }

}
