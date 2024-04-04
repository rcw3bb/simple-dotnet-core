# Simple .Net Core Gradle Plugin

The plugin that allows you access to dotnet commands inside gradle as task.

## Pre-requisite

* Java 8 (Minimum)
* Windows OS/Linux/MacOS

## Plugging in the simple-dotnet-core

In your **build.gradle** file add the following plugin:

```groovy
plugins {
    id "xyz.ronella.dotnet.core" version "3.0.0"
}
```

> A **Simple .Net Core tasks** group will be added to the available tasks at your disposal. You can use the following command to see them:
>
> ```
> gradlew tasks --group "Simple .NET Core"
> ```
>
> Expect to see the available tasks like the following:
>
> ```
> Simple .NET Core tasks
> ----------------------
> dotnetBuild - Build a .Net project.
> dotnetClean - Clean build outputs of a .NET project.
> dotnetCreateGlobalJson - Generate a global.json file.
> dotnetInfo - Display .NET Core information.
> dotnetListSDKs - Display the installed SDKs.
> dotnetMSBuild - Run Microsoft Build Engine (MSBuild) command.
> dotnetNewConsole - Create a new .NET Console project.
> dotnetPack - Create a NuGet package.
> dotnetPublish - Publish a .NET project for deployment.
> dotnetRestore - Restore dependencies specified in a .NET project.
> dotnetRun - Build and run a .NET project output.
> dotnetTask - Execute any available .Net Core SDK command.
> dotnetTest - Run unit tests using the test runner specified in a .NET project.
> dotnetVersion - Display .NET Core SDK version is use.
> ```

## DOTNET_CORE_HOME Environment Variable

The first location that the plugin will try to look for the **dotnet executable** will be the location set by **DOTNET_CORE_HOME** environment variable. If the plugin cannot detect the location of the installed **.Net Core SDK**, it is advisable to set this variable to the correct directory where the dotnet executable lives.

## Plugin Properties

| Property | Description | Type | Default |
|-----|------|------|-----|
| simple_dotnet.autoInstall | If set to true and the plugin doesn't find any dotnet executable,   the plugin will try to install it. The version of the .NET core SDK that will be installed will be based on the specified version on **global.json** if present. | boolean | true |
| simple_dotnet.baseDir | Tells the plugin what is the base directory it will work on *(e.g. like finding the global.json file.)*. | String | *The project directory* |
| simple_dotnet.verbose | The plugin will to display more information on the console *(e.g. the actual dotnet command being run)*. | boolean | false |

## Using dotnetTask

All the member tasks of Simple .Net Core group is a child for **dotnetTask**. The **child task** normally just have a default command and/or arguments *(e.g. **dotnetNewConsole** task has **new as the command** and **console as an argument**)*. 

Whatever you can do with the **dotnet command** in console you can do it in gradle with this task. 

#### Example

Translate the following **dotnet publish command** *(i.e. with .Net Core SDK 3.0)* into a task in gradle:

```
dotnet publish -r win-x64 -c Release /p:PublishSingleFile=true /p:PublishTrimmed=true
```

**Use the task itself using the following:**

```groovy
dotnetTask {
  command = 'publish'
  args = ['-r', 'win-x64', '-c', 'Release', 
          '/p:PublishSingleFile=true', '/p:PublishTrimmed=true']
}
```

**Use the child task dotnetPublish with the following:**

```groovy
dotnetPublish {
  args = ['-r', 'win-x64', '-c', 'Release', 
          '/p:PublishSingleFile=true', '/p:PublishTrimmed=true']
}
```

> You don't need to set the **command property** because it was already preset with **publish**.

**Create your own task of type DotNetTask like the following:**

```groovy
task publishToExe(type: DotNetTask) {
  command = 'publish'
  args = ['-r', 'win-x64', '-c', 'Release', 
          '/p:PublishSingleFile=true', '/p:PublishTrimmed=true']
}
```

> To use **DotNetTask class** as the type of your task, you must add the following at the top of your **build.gradle** file:
>
> ```
> import xyz.ronella.gradle.plugin.simple.dotnet.core.task.*
> ```
>
> Note: Each **default simple .net core tasks** has equivalent class file. The class file has the prefix **DotNet** *(and ending in **Task**)* instead of **dotnet** of the normal gradle task *(e.g. **dotnetPublish** gradle task has an equivalent class of **DotNetPublishTask**)*.

**Create your own task of type DotNetPublishTask for convenience like the following:**

``` groovy
task publishToExe(type: DotNetPublishTask) {
  args = ['-r', 'win-x64', '-c', 'Release', 
          '/p:PublishSingleFile=true', '/p:PublishTrimmed=true']
}
```

> You don't need to set the **command property** because it was already preset with **publish**.

## Sample build.gradle File

``` groovy
plugins {
  id "xyz.ronella.dotnet.core" version "3.0.0"
}

simple_dotnet.baseDir = project.projectDir.absolutePath

dotnetClean {
  doLast {
    delete 'bin'
    delete 'obj'
  }
}

dotnetPublish {
  args = ['-r', 'win-x64', '-c', 'Release', 
          '/p:PublishSingleFile=true', '/p:PublishTrimmed=true']
}
```

## Author

* Ronaldo Webb

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## [Build](BUILD.md)

## [Changelog](CHANGELOG.md)

