iOS Codebase Overview
=====================

This page will give a brief overview of the codebase as a whole, the architecture used for core and custom functionality and a high level overview of some of the challenge the code base faces in its current form.

.. note::  This documentation assumes that you wil be familiar with Black Label Solutions tech stack architecture at a high level, as well as the devices and technology involved with their POS solutions. Please talk to (Val) Valentin Causevski or Paul van't Klooster for an overview.

The codebase according to Xcode
-------------------------------

The iOS codebase consists of a single workspace with a project for each application.

.. image:: /_static/img/xcode-projects.png
    :width: 400px
    :align: center

.. Vertical whitespace hack
.. raw:: html

    <p></p>

.. note::
 - The workspace name `intellectual_property` came about because Black Label Solutions previously used to inherit client apps to work on and their needed to be a way to separate Black Label's code and IP from their clients. This should be re-named now as that type of work is not done anymore.
 - Projects with **POS** in their title indicate that the app is a `POS` application used by a company staff member.
 - The **nomaditunesPOSIOS** project is the one exception to the above rule as it is Black Label's consumer POS solution.
 - The `dormant` and `live` groups indicate the current status of a project but these can change. For example, some retailers like Foodworks only use their application once a year while others like the Reject Shop consumer app have been developed but never launched.


The codebase according to the file system
-----------------------------------------

Xcode's ability to group (structure) the code differently to how it's organized in the file system is used heavily in this workspace.

.. image:: /_static/img/filesystem-structure.png
    :width: 400px
    :align: center

The `optional`, `required` and `shared` directories contain code that each project points to that effectively are 99% of the files used to build an application.

How code is shared and re-used
------------------------------

The current strategy for code sharing and re-use is to have a single set of source files that all applications will use. This goes beyond being just an "engine" for a project, it means this code essentially IS each project.

The below shows where code will be in the Xcode project and where it will point to on the file system. This structure is identical for all projects.

.. image:: /_static/img/project-filesystem.png
    :width: 400px
    :align: center

In order for projects to have custom features, functionality, text and UI tweaks, the AppDelegate and a constants file is used.

.. image:: /_static/img/event-cinemas.png
    :width: 100%
    :align: center

.. warning::
    This architecture means that changing a core file (e.g. a model) in any project is actually changing that file for EVERY project.

The consequences of this are good (immediate consistency) and perhaps not good, depending upon the implications for changing functionality at the top level of a hierarchy where regressions may be caused in projects that have not been updated in some time.

Project AppDelegate as master and commander
-------------------------------------------

Each project has an AppDelegate which effectively acts as the captain of the ship and the rest of the codebase act as crew members. View Controllers for example will call the AppDelegate for configuration based calls such as what colour the total currency text should be, as well as an event handler for when products are added to a transaction.

The merits of this system were greater when the code base was smaller but as it has grown to a large size, this approach is now experiencing some tough issues:

  - AppDelegate is used for something it's not intended to (its the point for the OS to call into the application)
  - The mixing of configuration calls and business logic calls needs to be split out.
  - View Controllers and Views are not extendable, only configurable from the AppDelegate. This is not a problem now as the UI is standardized between clients but will make it difficult to handle UI customization requests as time progresses.
  - Difficult to know exactly what functionality has been customized by a Project only by looking at the AppDelegate. You must know object is delegating to the AppDelegate in order for each overridden method call.

Towards a new architecture?
---------------------------

Moving the core BLS files into their own project so that each specific project could consume a framework, static library or linked binary should be investigated.

This would enable releases of the BLS core to be controlled for each client and enable unit tests to be written against this project specifically.

It would also speed up compilation time for projects as the number of files for compiling would in most cases drop to the AppDelegate and the configuration files.

.. note::
 - Reused code (shared, optional, required) can be taken out and put into two separated projects: NomadCodebaseFramework(“required” folder) and NomadHardwareFramework (“optional” folder). The “shared” folder (just two class) could be merged into NomadCodebaseFramework. Then these two projects can be distributed as frameworks and shared across team.
 - The app delegation file should be cleaned. All theme related and hardware related code should be moved to another delegation file(s): AppSkinConfigurationDelegation for theming and AppHardwareConfigurationDelegation for included hardware resources.
 - Property list is recommended for skin configuration and hardware configuration since it’s more obvious and much easier to customise for different brand.
 - Recommend to use xib files for each UI page.
 - Unchanged resources like strings (localised as well), images and xib files should be put into framework project.

