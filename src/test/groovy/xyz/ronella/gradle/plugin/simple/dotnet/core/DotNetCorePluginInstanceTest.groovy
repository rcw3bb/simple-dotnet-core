package xyz.ronella.gradle.plugin.simple.dotnet.core


import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetListSDKsTask

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetBuildTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetNewConsoleTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPackTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetTestTask
import xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetVersionTask

class DotNetCorePluginInstanceTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testBuildTask() {
        assertTrue(project.tasks.dotnetBuild instanceof DotNetBuildTask)
    }

    @Test
    public void testCleanTask() {
        assertTrue(project.tasks.dotnetClean instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetCleanTask)
    }

    @Test
    public void testInfoTask() {
        assertTrue(project.tasks.dotnetInfo instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetInfoTask)
    }

    @Test
    public void testMsBuildTask() {
        assertTrue(project.tasks.dotnetMSBuild instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetMSBuildTask)
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
        assertTrue(project.tasks.dotnetPublish instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetPublishTask)
    }

    @Test
    public void testRestoreTask() {
        assertTrue(project.tasks.dotnetRestore instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRestoreTask)
    }

    @Test
    public void testRunTask() {
        assertTrue(project.tasks.dotnetRun instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetRunTask)
    }

    @Test
    public void testDotNetTask() {
        assertTrue(project.tasks.dotnetTask instanceof xyz.ronella.gradle.plugin.simple.dotnet.core.task.DotNetTask)
    }

    @Test
    public void testTestTask() {
        assertTrue(project.tasks.dotnetTest instanceof DotNetTestTask)
    }

    @Test
    public void testVersionTask() {
        assertTrue(project.tasks.dotnetVersion instanceof DotNetVersionTask)
    }

    @Test
    public void testListSDKsTask() {
        assertTrue(project.tasks.dotnetListSDKs instanceof DotNetListSDKsTask)
    }

}
