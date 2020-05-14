package xyz.ronella.gradle.plugin.simple.dotnet.core

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach

class DotNetCorePluginArgsTest {

    private Project project;

    @BeforeEach
    public void initProject() {
        project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'xyz.ronella.dotnet.core'
    }

    @Test
    public void testVersionTask() {
        assertEquals('--version', project.tasks.dotnetVersion.allArgs[0] as String)
    }

    @Test
    public void testInfoTask() {
        assertEquals('--info', project.tasks.dotnetInfo.allArgs[0] as String)
    }

    @Test
    public void testListSDKsTask() {
        assertEquals('--list-sdks', project.tasks.dotnetListSDKs.allArgs[0] as String)
    }

}
