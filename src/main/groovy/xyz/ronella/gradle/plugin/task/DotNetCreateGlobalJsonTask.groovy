package xyz.ronella.gradle.plugin.task

class DotNetCreateGlobalJsonTask extends DotNetTask {
    public DotNetCreateGlobalJsonTask() {
        super()
        description = 'Generate a global.json file.'
        command = 'new'
        internalArgs += 'globaljson'
    }
}