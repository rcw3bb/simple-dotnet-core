package xyz.ronella.gradle.plugin

/**
 * Support configuration for the plugin.
 *
 * @author Ron Webb
 * @since 2019-11-22
 */
class DotNetCorePluginExtension {

    /**
     * The main directory of the project where the plugin will work on.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    String baseDir

    /**
     * Controls the auto installation of the .Net Core SDK.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    boolean autoInstall = true
}
