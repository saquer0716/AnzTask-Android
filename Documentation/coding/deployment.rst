==========
Deployment
==========

.. rubric:: How we deploy apps for our clients and to the iTunes store.

.. warning:: This page assumes you've already imported the **BlackLabelSolutions.developerprofile** from ``resources/distribution/BlackLabelSolutions.developerprofile``.

Current state of deployment
===========================

Despite the size of some of our clients (e.g. Officeworks), they typically don't have the technical resources to take an `ipa` file we've produced and re-sign it with their Enterprise provisioning profile. 

As a result, we are normally given the certificates and provisioning profiles required to build and sign an `ipa` ready for installing onto a client's device.

As far as the NomadPOS application goes, it's a standard build for iTunes.

Historical Deployment Resources
===============================

If the resources in the `BlackLabelSolutions.developerprofile` do not provide you with the certificates or provisioning profiles required for deploying an application, look in the ``resources/distribution`` directory which is where all the certificates and provisioning profiles have been kept for historical purposes. **Note** that as these files are for historical purposes, many provisionsing profiles may be expired and ceritifcates may now be invalid. 



.. note:: We want to setup a CI server running OSX server that will act as a build and CI server for both Android and iOS applications. 