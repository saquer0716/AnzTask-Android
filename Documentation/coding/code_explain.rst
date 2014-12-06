============
Code Explain
============


AnzVolley Module
================

AnzVolley is a wrapper class on top of Google Volley and uses GSON to enhance the capability of RESTful web service.



.. note:: Earthquake occurrence could return with huge amount of data, so memory usage and fragmentation loading might need to be considered in the real implementation.

Circle Flip Button
==================


Log
===

Design Pattern
==============

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

.. important:: Even though we only have just on RESTful API here, but we still need to thing about how to make our work easier if in the future the API is updated or more API need to be supported. Use generic class and polymorphism should do the trick.

Module Structure
================

In terms of code resistibility, isolated function should be put into modules for local library distribution or remote distribution through Maven. Networking part should be considered to be maintained a separated project or module.

Design Pattern
==============

Design pattern could greatly increase code reusability and scalability。From the feature of networking requirement of this task, the networking module could use singleton, composition and factory design pattern.

Cross Platform
==============

It’s definitely easier to just use one HTML5 file with Javascript and CSS to do the job. Javascript bridge should be used to do WebView and native communication. Since this task in done in native Android and native iOS, there would be no cross platform support.

.. note:: With native c code we can still share code cross platform. The networking module can be developed in pure c code based on open source `curl` library. However this requires more work especially in cross platform building tool, so it’s off the table at the moment.