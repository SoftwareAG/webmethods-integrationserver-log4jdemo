# Log4j Demo for Integration Server

Since v10.5 Integration Server ships with Apache Log4j v2. This has caused issues with
some custom logging solutions, which rely on Log4j's default mechanism to find and process
configuration files.

This Integration Server package demonstrates an approach to control the loading 
and use of configuration. No changes to Tanuki Wrapper are necessary. Instead
a more explicit way is implemented with a few lines of Java code.

This package is not meant for production use but demonstration purposes.

------------------------------

These tools are provided as-is and without warranty or support. They do not constitute part of the Software AG product suite. Users are free to use, fork and modify them, subject to the license agreement. While Software AG welcomes contributions, we cannot guarantee to include every contribution in the master project.
