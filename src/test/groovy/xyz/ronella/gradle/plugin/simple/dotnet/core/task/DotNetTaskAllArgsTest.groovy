package xyz.ronella.gradle.plugin.simple.dotnet.core.task

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

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
        assertEquals(0, netTask.allArgs.size())
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

    @Test
    public void allArgsAreGenuineStringsWhenGStringsAreSupplied() {
        // Reproduces the "arraycopy: element type mismatch" ArrayStoreException that occurs when a
        // consumer assigns an interpolated GString (idiomatic in Gradle build scripts, e.g.
        // "-property:Foo=${bar}") to the args property: toArray(new String[]{}) requires every
        // element to be an actual java.lang.String, not merely a CharSequence.
        DotNetTask netTask = project.tasks.dotnetTask
        def placeholder = 'value'
        netTask.args = ["arg-${placeholder}", 'plainArg']

        netTask.allArgs.each {
            assertTrue(it instanceof String, "Expected a java.lang.String but was ${it.getClass()}")
        }

        // Would throw ArrayStoreException ("arraycopy: element type mismatch") before the fix.
        String[] argsArray = netTask.allArgs.toArray(new String[] {})

        assertEquals('arg-value plainArg', argsArray.join(' '))
    }

}
