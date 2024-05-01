# Changelog

## 3.1.0 : 2024-04-20

### Change

* Added support for MacOS.

## 3.0.0 : 2023-06-22

### Change

* Refactored to use lazy configuration of gradle 7.

## 2.0.1 : 2021-05-02

### Fix

* Add null check on determining the executable by environment variable.
* Use only the first executable when detecting the installed executables on Windows.

## 2.0.0 : 2020-05-15

### Fix

* Linux dotnet core installer is now marked as executable and the EOL is fixed.

### Change

* Change the location of the downloaded script.

### New

* Add simple.dotnet.core in the package name.

## 1.3.0 : 2020-04-21

### New

* Detection of the location of dotnet executable for both windows and linux.

## 1.2.0 : 2020-04-11

### New

* Auto .Net Core SDK installation for Linux

## 1.1.3 : 2020-04-10

### New

* Support to Linux.

## 1.1.2 : 2019-11-22

### Change

* Consider the command value before using the **--help** as the only argument.

## 1.1.1 : 2019-11-22

### Change

* If no command and arguments were specified use **--help** as the only argument.

## 1.1.0 : 2019-11-22

### New

* Auto .Net Core SDK installation.
* Introduce the plugin properties.

## 1.0.0 : 2019-11-19

### Initial Version

