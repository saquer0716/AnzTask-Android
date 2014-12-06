FAQ
===

How do I add new API support into AnzVolley module?
---------------------------------------------------

.. note:: 
 - Create the new request class which conforms to request parameter.
 - Create the new corresponding response class which conforms to the JSON response format. 
 - Go to AnzVolleyRestfulRequestFactory class and give the new API an ID and define it’s URL.
 - Still in AnzVolleyRestfulRequestFactory class, add another `switch case` code block for new API and that’s it.
