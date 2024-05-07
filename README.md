# OnlineLibraryProject <sup>TM</sup>
## Description
The OnlineLibraryProject is an ongoing effort to learn java by implementing a project.
Various concepts like file handling, access modifiers, collections like ArrayLists,
object passing as parameters, etc. are used.

Currently, as it stands the program is a CLI based application that acts a mock library system and 
has features like adding, issuing, looking up books. The entire data is stored in .csv files that 
are read by the program automatically.

Future aims involve integrating more features like
* Search system instead of displaying the entire database at once.
* Making a visually appealing UI using Swing or JavaFX.
* integrating an actual database using DBMS instead of exel sheets to store data.
* Authentication system in place for admin access or normal access.

### 1. The books data when displayed is of the format : 

|      NAME      | AUTHOR | LANGUAGE | ID    |   GENRE   | 
|:--------------:|:------:|:--------:|-------|:---------:|
| Dukh ke Dastan |   Me   |  Hindi   | OL001 | self help |

obviously displayed on the terminal formatted by pipelines.

### 2. The options List looks like : 
```
Choose what you want to do in our online Library 
1. See information about the onlineLibraryProject 
2. Show available books 
3. Add or return books 
4. issue books 
5. See the issued books 
6. Exit from the online library 
```

### Technical details summary
* The database stored in .csv format is read using `java.io.BufferedReader`
class and the lines read are separated using `.split()` String method.
* The separated Strings are stored in a multidimensional ArrayList representing a table of sorts 
and then manipulated.
* The database is read at runtime through the `library_V3()` Constructor. Then after all the manipulations,
on exiting the program, the files are automatically saved in the same previous format.
