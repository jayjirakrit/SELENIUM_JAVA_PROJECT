# UiTestAutomation

UiTestAutomation is used for automate testing workflow in Ui layer. Design Pattern is Page Object Model

Used tools and frameworks
---------------------------------------
1. Selenium
2. Maven repository 
3. TestNG
4. Extend Report

## Versioning
1. Java 17
2. Maven 3.8

## Running Test Suites

Run `mvn clean test -DSUITEFILE={TestSuitePath}` to execute the Test Suites from commandline
Ex. `mvn clean compile test -DSUITEFILE=src/main/resources/xml/framework/e2e_login.xml`

## Report File

Find Summary-Report{DateTime}.html file in Path:`test-output\UI*.html`.

## Website UI Testing
https://the-internet.herokuapp.com/

## Further help

To get more help on the Maven CLI use `mvn -help` or go check out the [Maven CLI Overview and Command Reference](https://maven.apache.org/index.html) page.

To get more help on Selenium go check out (https://www.selenium.dev/) page.
#
# Code Conventions for Java
The information on this page is for Archive Purposes Only

This page is not being actively maintained. Links within the documentation may not work and the information itself may no longer be valid. The last revision to this document was made on April 20, 1999

### Naming Conventions
Naming conventions make programs more understandable by making them easier to read. They can also give information about the function of the identifier-for example, whether it's a constant, package, or class-which can be helpful in understanding the code.

| Identifier Type | Rules for Naming | Examples |
|:---------------:|:-----------------|:---------|
|Packages|The prefix of a unique package name is always written in all-lowercase ASCII letters and should be one of the top-level domain names, currently com, edu, gov, mil, net, org, or one of the English two-letter codes identifying countries as specified in ISO Standard 3166, 1981. Subsequent components of the package name vary according to an organization's own internal naming conventions. Such conventions might specify that certain directory name components be division, department, project, machine, or login names.| com.sun.eng com.apple.quicktime.v2 edu.cmu.cs.bovik.cheese|
|Classes|Class names should be nouns, in mixed case with the first letter of each internal word capitalized. Try to keep your class names simple and descriptive. Use whole words-avoid acronyms and abbreviations (unless the abbreviation is much more widely used than the long form, such as URL or HTML).|class Raster; <br>class ImageSprite;|
|Interfaces|Interface names should be capitalized like class names.|interface RasterDelegate; <br>interface Storing;|
|Methods|Methods should be verbs, in mixed case with the first letter lowercase, with the first letter of each internal word capitalized.|run();<br>runFast(); <br>getBackground();|
|Variables|Except for variables, all instance, class, and class constants are in mixed case with a lowercase first letter. Internal words start with capital letters. Variable names should not start with underscore _ or dollar sign $ characters, even though both are allowed.Variable names should be short yet meaningful. The choice of a variable name should be mnemonic- that is, designed to indicate to the casual observer the intent of its use. One-character variable names should be avoided except for temporary "throwaway" variables. Common names for temporary variables are i, j, k, m, and n for integers; c, d, and e for characters.|int i;<br>char c; <br>float myWidth;|
|Constants|The names of variables declared class constants and of ANSI constants should be all uppercase with words separated by underscores ("_"). (ANSI constants should be avoided, for ease of debugging.)|static final int MIN_WIDTH = 4;<br>static final int MAX_WIDTH = 999; <br>static final int GET_THE_CPU = 1; static final int INVERTMENT_LOGIN_MAX = 1;<br> static final String CUSTOMER_SERVICE_LOGIN_MIN = "3";|

###  File Names

This section lists commonly used file suffixes and names.


- File Suffixes
    - Java Software uses the following file suffixes:

| File Type | Suffix | 
|:---------------:|:-----------------|
|Java source|.java|
|Java bytecode|.class|

- Common File Names
    - Frequently used file names include

| File Name | Use |
|:---------------:|:-----------------|
|GNUmakefile|The preferred name for makefiles. We use gnumake to build our software.|
|README|The preferred name for the file that summarizes the contents of a particular directory.|

