package xyz.ronella.gradle.plugin.simple.dotnet.core

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import java.nio.file.Paths

import static org.junit.jupiter.api.Assertions.*

class DotNetCorePluginCommandTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testBuildTask() {
        assertEquals('build', project.tasks.dotnetBuild.command)
    }

    @Test
    public void testCleanTask() {
        assertEquals('clean', project.tasks.dotnetClean.command)
    }

    @Test
    public void testMsBuildTask() {
        assertEquals('msbuild', project.tasks.dotnetMSBuild.command)
    }

    @Test
    public void testPackTask() {
        assertEquals('pack', project.tasks.dotnetPack.command)
    }

    @Test
    public void testPublishTask() {
        assertEquals('publish', project.tasks.dotnetPublish.command)
    }

    @Test
    public void testRestoreTask() {
        assertEquals('restore', project.tasks.dotnetRestore.command)
    }

    @Test
    public void testRunTask() {
        assertEquals('run', project.tasks.dotnetRun.command)
    }

    @Test
    public void testDotNetTask() {
        assertEquals('', project.tasks.dotnetTask.command)
    }

    @Test
    public void testTestTask() {
        assertEquals('test', project.tasks.dotnetTest.command)
    }

    @Test
    public void testDotNetTaskExecuteCommand() {
        project.extensions.simple_dotnet.baseDir = Paths.get(".").toAbsolutePath().toString()
        project.extensions.simple_dotnet.verbose = true
        project.tasks.dotnetTask.executeCommand()
    }

}