============
App Features
============

The earthquake occurrence app is quite simple and the UI part is done in two hours while the networking module took another two hours to get finished.

App features include:

 - Clean and simple UI, use Google’s latest Roboto font for text rendering
 - Swipe refresh to get updated earthquake occurrence data in background thread
 - Show region, date, magnitude and depth in ListView cell
 - Support ascending and descending sort by magnitude and depth
 - User click to switch to show between magnitude and depth
 - Navigation to Google Map app to show coordinate of earthquake
 - Great RESTful API scalability 
 

App Functionality Scalability
=============================

 - New RESTful API could be added and tested in less than 5 minutes
 - New sorting method can be added by simply copying and pasting existing code with little change
 - The flip button widget can be used in any project

App Restriction
===============

 - Get earthquake occurrence API returns a large amount of data and there is no way to load it in segments and there might be a potential memory and long loading time issue if too much data is returned without any restriction. Should fix by modifying this API by adding range parameters.
 - Only supporting sorting by magnitude and depth. Should also support sorting by date and location. However, this can’t not be done flexibly since the returned JSON data returns date in plaint string which is not quite appropriate for implementation. Changing date property into mili seconds would be much more flexible for end user development to add supports for sorting and grouping by year, month, week and day.
 - The app is only tested on Samsung S4 and it might have UI issue on devices with small screen.
 - Due to limited time, content provider is not implemented for local earthquake data storage. 

App Screenshots
===============

App is first loaded

.. image:: /_static/img/screenshot/start.png
    :width: 400px
    :align: center

 

Finished loading data through RESTful web service

.. image:: /_static/img/screenshot/loaded.png
    :width: 400px
    :align: center



Flip the button to show earthquake depth

.. image:: /_static/img/screenshot/flip.png
    :width: 400px
    :align: center



Supported sorting capability

.. image:: /_static/img/screenshot/sort.png
    :width: 400px
    :align: center



Swipe to refresh the data

.. image:: /_static/img/screenshot/refresh.png
    :width: 400px
    :align: center



Show data in depth

.. image:: /_static/img/screenshot/depth.png
    :width: 400px
    :align: center



Navigate to Google Map

.. image:: /_static/img/screenshot/map.png
    :width: 400px
    :align: center
		

