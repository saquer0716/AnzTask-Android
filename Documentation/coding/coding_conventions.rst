==================
Coding Conventions
==================

.. rubric:: Android follows most of JAVA coding conventions

Traditional Naming Conventions
==============================

 - Member variables always start with `m`
 - Use camel style for class and interface name
 - Local variable start with lower case and each following word start with upper case
 - Method start with lower case and each of following word start with upper case
 - Don’t use abbreviations as it might confuse developer from different country
 - Constant must be upper case for all letters

Android Naming Conventions
==========================

 - It’s OK to add company name into class name, like `AnzLog`
 - Layout file should be related to class file name. For example, We have an Activity with  name `EarthquakeActivity`, then its layout file should be named `activity_earthquake`
 - View cell layout should start with `view_cell` followed by list view’s name, so when we have more than one view cell layout files, it’s easier to locate in project explorer.
 - Resources files or variables should tell where they are used o what they do. For example, a selector xml file should always start with `selector` then followed by widget like button, i.e. selector_button.xml

Project Format
==============

Project folder

.. image:: /_static/img/screenshot/project_structure.png
    :width: 215px
    :align: center

Project folder explanation

 - ``activity``
   Group all activities in this folder
 - ``fragment``
   Group all fragment in this folder
 - ``adapter``
   Group all list view adapter in this folder
 - ``model``
   Put model class and content provider class in this folder
 - ``net``
   All networking code should be put in this folder
 - ``ui``
   Group all custom view or widgets in this folder
 - ``util``
   Group utility class like formatter, comparator, etc. in this folder
 - Third party source code should be put in separated package
