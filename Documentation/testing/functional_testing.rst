===============
Functional Test
===============

Android Studio Functional Test
==============================

Android Studio provides with very convenient way for functional test. If you don’t know how to set up functional test in Android Studio, please refer to Android functional test webpage.

Test Case
==================

Test app based on app features would be good for TDD. From ANZ Task app’s features, a few test cases are beset up: 

 - testMainActivityLaunch (test that the app can be successfully launched)
 - testEarchquakeListView (test earthquake data is populated into list view)
 - testEarchquakeListViewCell (test list view’s cell displays correct data property)
 - testFlipButton (test that flip button can be flipped)
 - testNavigationListSort (test the list view can be sorted by clicking navigation list)
 - testNavigationGoogleMap (test google map can be launched by clicking list view item)

.. note:: 

 - Test case name might be different from above. refer to project/app/src/androidTest/functional for the test source code.

Test Report
===========

 - `Funcional Test Report <../_static/test_report/functional/index.html>`_
