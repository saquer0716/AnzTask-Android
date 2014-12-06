(function(window, undefined) {
  var dictionary = {
    "2927dbc8-83e1-4bc9-8009-fe270c54a80a": "Booking",
    "2a0258c0-4050-426a-823d-0cd837204d7d": "Confirm",
    "0e07d03c-0f93-4744-b2d8-0d811dba5763": "Settings",
    "a920f88b-6cfc-48c5-8bc6-93d1f3666751": "Recent",
    "75228f95-7a2a-447a-ba75-2df8e142c33a": "Collect",
    "47751252-9d1c-4fba-a95e-a75c4d28c2df": "Session",
    "2bac39e7-c700-42b4-8779-3f680facd75f": "Ticket",
    "d12245cc-1680-458d-89dd-4f0d7fb22724": "Start",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "Template 1"
  };

  var uriRE = /^(\/#)?(screens|templates|masters)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);