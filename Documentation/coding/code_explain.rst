============
Code Explain
============


AnzVolley Module
================

A good networking library should have following features:

 - Network requests must be asynchronous and happen outside UI thread.

 - Network response is always brought back in main thread.

 - Network cache in local storage.

 - Simple response method: onSuccess, onError. onSuccess comes with generic arguments for different API request.

 - Support response of JSON and binary file.

 - Support multiple threading, customizable thread pool. FIFO queue or based on request priority. 

 - Support cancelling of current running or pending network request.

 - Network request is able to keep running when app exits if necessary.

 - Support OAuth2.0, manages(exchange, request and refresh) token internally.

There is also another concern about image downloading, even thought it’s not required by ANZ task, but still worth of discussing.

 - Cache images in memory.

 - Save thumbnails in storage.

 - Only load images for items shown on screen.

Google Volley almost comes with all above features. However, to make full use of its JSON capability and add good scalability for our earthquake occurrence RESTful API, we need AnzVolley module.

AnzVolley is a wrapper class on top of Google Volley and uses GSON to enhance the capability of RESTful web service. 

Use AnzVolley is quite simple, just create a predesign request and add it to request queue. So there are two players here to do the job: Request and AnzVolley. 

AnzVolley itself is a singleton class because we want to access this resource from anywhere in the project and all request share the same request queue, there is no need to have multiple instance of AnzVolley class.

Request is generated through `AnzVolleyRestfulRequestFactory` class’s factory method. Because every RESTful API request (RequestEarthquake, RequestWeather, etc.) inherits from the same `RequestObject` class, it’s just so easy to use just one factory method to generate all different kinds of products (request). 

After request instance is produced, adding it to request queue would start http request right away. Now we have a problem, how to mapping the response to the right request? The answer is generic class.

Without generic class, we have to define new factory method for new RESTful API request because the response class is different. With generic class, we can specify the type of response as `T` and cast the response JSON string like this:
T parsedGSON = mGson.fromJson(jsonString, mType);
and then return the parsedGSON through interface call back.

With generic class and factory design pattern, scalability couldn’t be much better. If we need to add another RESTful API to current AnzVolley module, just do following steps:

 - Create the new request class which conforms to request parameter.
 - Create the new corresponding response class which conforms to the JSON response format. 
 - Go to AnzVolleyRestfulRequestFactory class and give the new API an ID and define it’s URL.
 - Still AnzVolleyRestfulRequestFactory class, add another case code block for new API and that’s it.

.. note:: New API request and response’s member variables should have exact the same name as in JSON’s key name. Although this is not mandatory but good practice. Please refer GSON’s website for more information.

Circle Flip Button
==================

Circle Flip Button is a mimic of Android Gmail’s mail delete button. The principle behind it is using ObjectAnimator to do a `flipY` animation. However, flip the view 180 degrees would also cause the text to be flipped. The best solution is splitting the 180 degrees flip animation into two parts: from 0 degree to 90 degrees animation and then from -90 degrees to 0 degree animation. This trick gives a fake impression that the view flips 180 degrees in one direction but actually it flip 90 degrees in one direction and then 90 degrees in another direction, but still keep the text in correct side.

Log
===

There are two custom log class (AnzLog and AnzVolleyLog) in this project, both of which are just a simple and thin wrapper on top of JAVA Log class. The extra capability these custom log class is a boolean flag to turn on or turn off logs for different builds. Normally we use logs in debug stage and disable logs when release. But this is relatively tedious to do in Eclipse because flag needs to be manually cleared for release. Now, Thanks to Android Studio’s build mechanism, this can be easily achieved by checking `BuildConfig.DEBUG` to turn on or off logs output.

Design Pattern
==============

Build Script
============

