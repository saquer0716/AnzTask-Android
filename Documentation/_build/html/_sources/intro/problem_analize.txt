================
Problem Analysis
================

Below is a list of thoughts about this task.

UI and Navigation
=================

To present earthquake occurrence, one activity with a full screen ListView should be enough to do the work. By investigating the JSON response of RESTful API, each earthquake data comes with quite a lot of information(properties) which would make UI very squeezed if all of them are displayed. Therefore, some simple user interactions could be introduced to get access to more information.

{"count":"21740","earthquakes":[{"src":"us","eqid":"c000is61","timedate":"2013-07-29 22:22:48","lat":"7.6413","lon":"93.6871","magnitude":"4.6","depth":"40.90","region":"Nicobar Islands, India region"},{"src":"us","eqid":"c000is4s","timedate":"2013-07-29 21:52:12","lat":"-57.7816","lon":"-25.3260","magnitude":"5.2","depth":"53.50","region":"South Sandwich Islands region"},{"src":"us","eqid":"c000is3k","timedate":"2013-07-29 21:33:34","lat":"36.6696","lon":"71.0615","magnitude":"4.7","depth":"234.10","region":"Hindu Kush region, Afghanistan"},{"src":"us","eqid":"c000irvf","timedate":"2013-07-29 18:27:41","lat":"-37.2993","lon":"177.2515","magnitude":"4.9","depth":"160.50","region":"off the east coast of the North Island of New Zealand"},{"src":"us","eqid":"c000irpf","timedate":"2013-07-29 14:53:32","lat":"24.5038","lon":"62.5255","magnitude":"4.5","depth":"24.30","region":"off the coast of Pakistan”}}

Even though there is no requirement for Google Map, however, given that earthquake location can be obtained from RESTful API response, it’s good idea to start Google Map when any ListView cell is clicked. Navigation to system Google Map app is preferred because it’s easier to implement and also meet our needs.

.. note:: Earthquake occurrence could return with huge amount of data, so memory usage and fragmentation loading might need to be considered in the real implementation.

Networking
==========

Developers tend to use third party networking library for RESTful and other web service implementation. There is no need to make wheel unless particular requirements must be met.

Popular third party RESTful libraries include:

 - Google Volley
 - RESTDroid
 - RoboSpice

Google Volley is highly recommended here because of it’s powerful capability and advantages:

 - Open source
 - Easy API
 - Also support image lazy loading
 - Http cache

.. important:: Even though we only have just on RESTful API here, we still need to thing about how to make our work easier if in the future the API is updated or more API need to be supported. Use generic class and polymorphism should do the trick.

Module Structure
================

In terms of code resistibility, isolated function should be put into modules for local library distribution or remote distribution through Maven. Networking part should be considered to be maintained in a separated project or module.

Design Pattern
==============

Design pattern could greatly increase code reusability and scalability。From the feature of networking requirement of this task, the networking module could use singleton, composition and factory design pattern.

Cross Platform
==============

It’s definitely easier to just use one HTML5 file with Javascript and CSS to do the job. Javascript bridge should be used to do WebView and native code communication. Since this task in done in native Android and native iOS, there would be no cross platform support.

.. note:: With native c code we can still share code across platform. The networking module can be developed in pure c code based on open source ``curl`` library. However, this requires more work especially in cross platform building tool, so it’s off the table at the moment.

Content Provider
================

Although not required, providing local database through content provider would be good practice for such scenario. Local DB would improve user experience and saves mobile data usage.
