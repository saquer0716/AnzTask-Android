==========================
Identified Codebase Issues
==========================

.. rubric:: Every codebase has their warts, these are ours. Let's fix them!

Current identified issues
=========================

 * Inconsistent formatting (re-format all, then use Objective-Clean to ensure formatting remains consitent)
 * Split ``required`` files into static library in separate project. Define process for how client and NomadPOS projects get the stable releases of core static binary and bundle).
 * Identify and remove unused, variables and methods.
 * Enable ARC.
 * Upgrade to Objective-C 2.0 to remove unnecessary boilerplate.
 * Remove encode/decode for models that aren’t persisted to disk.
 * Change #DEFINE to constants for objects (e.g. NSStrings).
 * AppDelegate being used for config, need to move to project specific plists.
 * Turn on warnings as errors and fix `errors` accordingly.
 * Remove un-needed dependencies (e.g. Verifone deprecated).
 * Many projects need to be updated to recommended settings for Xcode 6.

