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
    public String baseDir

    /**
     * Controls the auto installation of the .Net Core SDK.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    public boolean autoInstall = true

    /**
     * Controls the amount of information displayed on the console.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    public boolean verbose = false

    /**
     * Utility method for printing text on the console based on the verbose property.
     * @param text The value to be printed.
     *
     * @author Ron Webb
     * @since 2019-11-22
     */
    public void writeln(String text) {
        if (verbose) {
            println(text)
        }
    }

}
