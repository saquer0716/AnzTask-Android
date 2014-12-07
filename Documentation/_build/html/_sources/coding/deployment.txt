====================
Build and Deployment
====================

Build and Release
=================

.. warning:: 
 - AnzTask Android project is developed in Android Studio V0.8.8. It should be building and running without any error in the same version of Android Studio.
 - Since more than 80% devices are running on Jelly Beans and above, AnzTask Android project is build in minimum version of 16 and targets version is 19, i.e. Kitkat. Please make sure you have the right Android SDK version installed. For more information about Android versions statistics, refer to: https://developer.android.com/about/dashboards/index.html?utm_source=ausdroid.net
 - AnzTask also only targets normal screen devices with at least ldpi resolution because this group of devices takes more than 70% of all Android devices.
 - The build tool should be at least 20.0.0

The released APK file in Binary folder can be released directly to customer because it’s signed with released key and obscured with proguard. 

The released key is saved in Binary folder with the release APK file. its credential is like follows:

Key store password: ``anzanz``

key alias: ``ANZ``

key password: ``anzanz``

Deployment
==========

APK file can be deployed to Android devices through SD card installation and USB silent installation.

To install APK file from USB, make sure the device is in debug mode and Android SDK is installed in host machine. Then type ``adb install -r app-release.apk`` in command line to install the app. You might need to navigate to the Android SDK’s platform-tools folder to get access to adb command (or set up envirionment variable for adb command path).
