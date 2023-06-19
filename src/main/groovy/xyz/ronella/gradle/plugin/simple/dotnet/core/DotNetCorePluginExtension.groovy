package xyz.ronella.gradle.plugin.simple.dotnet.core

import org.gradle.api.provider.Property

/**
 * Support configuration for the plugin.
 *
 * @author Ron Webb
 * @since 2019-11-22
 */
abstract class DotNetCorePluginExtension {

    /**
     * The main directory of the project where the plugin will work on.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    abstract Property<String> getBaseDir()

    /**
     * Controls the auto installation of the .Net Core SDK.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    abstract Property<Boolean> getAutoInstall()

    /**
     * Controls the amount of information displayed on the console.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    abstract Property<Boolean> getVerbose()

    /**
     * Utility method for printing text on the console based on the verbose property.
     * @param text The value to be printed.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    void writeln(String text) {
        if (verbose.get()) {
            println(text)
        }
    }

    DotNetCorePluginExtension() {
        autoInstall.convention(true)
        verbose.convention(false)
    }

}
