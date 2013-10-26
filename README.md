BytecodeASTGenerator
====================

An Abstract Syntax Tree generator from Java bytecode through the integration of ASM Engineering Library. (For research purpose, currently set to only produce Android API related result in matrix format)

##Usage:
###1. Compile:
`make compile`
###2. Run:
`make run file="jarfilename"`
> jarfilename is the filename of the .jar file that you want to interpret, eg. ciku.jar

###3. Clean Build:
`make clean`

##Result Data:
The result would be generated within the same folder and with the same .jar filename in .txt format

###Sample Output:
	ciku-android/database/sqlite/SQLiteOpenHelper <init>-0,android/database/sqlite/SQLiteOpenHelper <init>
	ciku-android/database/sqlite/SQLiteDatabase close-0,android/database/sqlite/SQLiteDatabase close
	ciku-android/database/Cursor close-0,android/database/Cursor close
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/sqlite/SQLiteDatabase delete
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/sqlite/SQLiteDatabase query
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/Cursor close
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/Cursor moveToFirst
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/sqlite/SQLiteDatabase insert
	ciku-android/database/sqlite/SQLiteDatabase delete-0,android/database/sqlite/SQLiteDatabase update
	ciku-android/database/sqlite/SQLiteDatabase delete-1,android/database/sqlite/SQLiteDatabase delete
	ciku-android/database/sqlite/SQLiteDatabase delete-1,android/database/sqlite/SQLiteDatabase query

each line is an individual relationship between function and function. The functions seperate by ',' and which means the first function has relationship with the second function (will be executed/called togehter) in one of these condition:
1. Return value
2. Argument
3. Be called as Object
