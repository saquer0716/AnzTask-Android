=====================
Product Image Loading
=====================

.. warning:: 
	This functionality is subject to QA verification and not yet enabled by default. In order to enable, you must add a pre-processor macro of ``NEW_PRODUCT_IMAGES_FEATURE=1`` to the project target. Currently, it is enabled only for Event Cinema's and only for Debug and Ad Hoc builds. 
	
	If you want to add this functionality to a new part of the app, for now, wrap the code in ``#ifdef NEW_PRODUCT_IMAGES_FEATURE {...} #endif`` block to protect the inner code from executing in a production release/enterprise release.
	
	Ensure you give QA an Ad Hoc and not Enterprise build for testing, otherwise they will not see any product images.

Product image loading is done via the BLProductImageLoader which exposes a singleton accessor. The class inheritance heirarchy can be seen below.

.. graphviz:: /_diagrams/product_image_loading.dot

.. note:: The BLPersistentRemoteImageManager makes use internally of the BLCacheFilesManager to persist the NSData bytes to disk.

How the BLProductImageManager is used
=====================================

Product images are loaded upon request by view controllers who are responsible for displaying product images. In other words, on demand.

While it would be easy to download all product images based on the inventory for a store, there is no way of knowing if all images should be downloaded (some stores have over 100,000 products) or which images out of a large product inventory should be downloaded. As a result, we only download images as the UI demands it.

How it works
============

There is good unit test coverage for the product image loading functionality and viewing the tests is the best way to understand the the design decisions, functionality and limitations of the current system.
