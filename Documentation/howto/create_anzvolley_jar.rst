==========================
How to build AnzVolley Jar
==========================


AnzVolley Jar
=============

To export AnzVolley in Jar file format, do as following steps:

 - Open build.gradle file in AnzVolley module in Android Studio.
 - make sure build script looks like following image.
 - Press Option+F12 to open terminal in Android Studio.
 - Make sure you are in root folder of AnzTask project.
 - Run ./gradlew makeJar
 - After build finishes, you will find libAnzVolley.jar file in /AnzVolley/build/outputs

.. image:: /_static/img/screenshot/code.png
    :width: 400px
    :align: center