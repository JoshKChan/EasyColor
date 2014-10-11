EasyColor by Joshua Chan

EasyColor is a command line tool to help coordinate the color schemes of programs.
Supported Programs:
    bblean
    Rainmeter
    

Requirements

    Windows
    Java 1.7.0_67
    
Using EasyColor

    To use EasyColor with the EasyColor.jar file, open command prompt in the same
    folder as EasyColor.jar. Type in:
    
        java -jar EasyColor.jar
        
    From there on use the commands in the next section to control the program.
    
Formatting Schemes

The scheme files use an arbitrary extension (.sch) to distinguish them. They can be read as plain text files.
Note that EasyColor reads these files using the InternalReader, so lines beginning with a semicolon ';', will
not be processed and can be used to place comments.

At the very beginning of a scheme is where user defined colours should be defined. 
These should be provided in hex.
Below them, the headers break up different files and their specific colour schemes. There may be duplicate types, since
programs such as Rainmeter may have multiple modules that use multiple files. As a short example,
    
    red=#ff0000
    green=#00ff00
    [Program|filePath]
    ;Explicit colour assignemnt
    label1=#ff0000
    ;Colour assignment using user defined labels
    label2=red
    ...
    
Commands
    exit - Exit the program
    read - Reads the files specified in "path.txt" to make a scheme file.
           If there is no "path.txt" file, an example will be generated.
    read c - Same as read, but keeps the original colors in the scheme file.
    apply   - Uses "scheme.schm" to apply colors to the appropriate files.
              Automatically makes backups of the originals before editing.
