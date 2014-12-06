====
CORE
====

.. rubric:: Issues to be fixed and how to fix them. Should potentially be put in Jira.

The term "core" will (for now) refer to any code in the ``required`` directory. It is code shared across **ALL** projects.

Issues to fix
=============

A list of things to fix and re-factor. Use

BLObject > documentFolder:isBidirectional
-------------------------

Whenever a file is persisted to disk, this class method is usually called to get the path to which it should be saved. It's problematic for a couple of reasons:
  * Has code which implements special functionlaity if the app is running in the sim. This should be remove.
	* It has a flag ``isBidirectional`` which seems to specifify whether a path to the Documents or Caches directory should be provided. We now have ``BLCacheFilesManager`` and ``BLDocumentFilesManager`` and code should be explicitly calling one or the other so it's obvious where the file will end up. This will also save the calling code saving the file to disk as these managers do this.
	
To fix, all usages need to be identified and replaced with calls to either the Cache or Document file managers and then, this method should be removed.