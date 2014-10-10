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

Commands
    exit - Exit the program
    read - Reads the files specified in "path.txt" to make a scheme file.
           If there is no "path.txt" file, an example will be generated.
    read c - Same as read, but keeps the original colors in the scheme file.
    apply   - Uses "scheme.schm" to apply colors to the appropriate files.
              Automatically makes backups of the originals before editing.
