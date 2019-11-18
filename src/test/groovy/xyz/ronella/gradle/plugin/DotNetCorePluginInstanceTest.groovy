package xyz.ronella.gradle.plugin

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import xyz.ronella.gradle.plugin.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.task.DotNetCleanTask
import xyz.ronella.gradle.plugin.task.DotNetInfoTask
import xyz.ronella.gradle.plugin.task.DotNetMsBuildTask
import xyz.ronella.gradle.plugin.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.task.DotNetPackTask
import xyz.ronella.gradle.plugin.task.DotNetPublishTask
import xyz.ronella.gradle.plugin.task.DotNetRestoreTask
import xyz.ronella.gradle.plugin.task.DotNetRunTask
import xyz.ronella.gradle.plugin.task.DotNetTask
import xyz.ronella.gradle.plugin.task.DotNetTestTask
import xyz.ronella.gradle.plugin.task.DotNetVersionTask

class DotNetCorePluginInstanceTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'simple.dotnet.core'
    }

    @Test
    public void testBuildTask() {
        assertTrue(project.tasks.dotnetBuild instanceof DotNetBuildTask)
    }

    @Test
    public void testCleanTask() {
        assertTrue(project.tasks.dotnetClean instanceof DotNetCleanTask)
    }

    @Test
    public void testInfoTask() {
        assertTrue(project.tasks.dotnetInfo instanceof DotNetInfoTask)
    }
    @Test
    public void testMsBuildTask() {
        assertTrue(project.tasks.dotnetMsBuild instanceof DotNetMsBuildTask)
    }
    @Test
    public void testNewConsoleTask() {
        assertTrue(project.tasks.dotnetNewConsole instanceof DotNetNewConsoleTask)
    }
    @Test
    public void testPackTask() {
        assertTrue(project.tasks.dotnetPack instanceof DotNetPackTask)
    }
    @Test
    public void testPublishTask() {
        assertTrue(project.tasks.dotnetPublish instanceof DotNetPublishTask)
    }
    @Test
    public void testRestoreTask() {
        assertTrue(project.tasks.dotnetRestore instanceof DotNetRestoreTask)
    }
    @Test
    public void testRunTask() {
        assertTrue(project.tasks.dotnetRun instanceof DotNetRunTask)
    }
    @Test
    public void testDotNetTask() {
        assertTrue(project.tasks.dotnetTask instanceof DotNetTask)
    }
    @Test
    public void testTestTask() {
        assertTrue(project.tasks.dotnetTest instanceof DotNetTestTask)
    }
    @Test
    public void testVersionTask() {
        assertTrue(project.tasks.dotnetVersion instanceof DotNetVersionTask)
    }

}
