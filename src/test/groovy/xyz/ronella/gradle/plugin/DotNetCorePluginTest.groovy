package xyz.ronella.gradle.plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import xyz.ronella.gradle.plugin.task.DotNetNewConsoleTask

class DotNetCorePluginTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'simple.dotnet.core'
    }

    @Test
    public void newTaskTest() {
        def console = project.tasks.dotnetNewConsole as DotNetNewConsoleTask
        console.executeCommand()
    }

}
