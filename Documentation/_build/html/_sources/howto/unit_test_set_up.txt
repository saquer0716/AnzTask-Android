=========================================
How to Set Up Unit Test in Android Studio
=========================================


Unit Test
=========

To set up unit test in Android Studio, do as following steps:

 - Select ``Run/Debug Configurations dropdown list``. 
 - Click ``Edit Configurations``.
 - In Run/Debug configurations window, click `+` button in top left and select ``Android Test``
 - On the right panel, specify new configuration ``name`` and select module that needs to be tested in ``module dropdown list``.
 - Check ``USB device`` in target device and click OK.
 - A new configuration appears in Run/Debug Configurations dropdown list. Select the new unit test configuration and click ``Run``, Android Studio will start unit test.
 - The work left is going to ApplicationTest file in androidTest folder and adding test cases.
