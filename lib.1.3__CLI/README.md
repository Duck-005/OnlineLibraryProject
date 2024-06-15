# CLI Version

A command line interface for the OnlineLibraryProject. <br>
The formatting of data is taken care but since all the data is displayed all at once, having a large database
is not recommended for CLI version ( lib.1.3 ). 

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
