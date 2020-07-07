We expect you to provide a Java console application that implements Counting Code Lines. 
The functionality is described here http://codekata.com/kata/kata13-counting-code-lines/

## Requirements
1. It should be a simple java application without Spring or any other frameworks. 
2. The application should have CLI which allows user to provide file/folder name
3. The result should be printed to console
4. The result should be printed in a well-formatted manner.

If a single file is provided as an input, the result has to be in the form of "<filename> : <number of lines>". E.g. "App.java : 42"
If a directory name is provided as an input result should include aggregated values as well, e.g. root : 331

subfolder1 : 140 Class1.java : 65 Class2.java : 75

subfolder2 : 161 Class3.java : 102 Class4.java : 59

5. Unit tests are expected. You can use the framework of your choice.
6. Please do adhere to Google Java Style Guide https://google.github.io/styleguide/javaguide.html