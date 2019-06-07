#!/bin/bash
###############################
# get number of files with given extension
#Input: $1=path, $2=extension

function amount_files () {
  AMOUNT=$(ls -1p $1/*.$2 | wc -l)
}
###############################
#read a path name from console

function read_dir() {
  echo "Enter directory path:"
  read DIR
  if  [ ! -d $DIR ];
    then echo "Directory dosn't exist"
  fi
}
###############################
# Menu

select VAR in "About us" "Delete files" "Find file with longest name" "Exit"
do 
  case $VAR in

##############################
# Info about developer

  "About us")
     echo "Developed by Dennis Tikhomirov"
   ;;
##############################  
# Exit from programm

  "Exit")
     echo "Good bye!"
     exit
  ;;
##############################
# Delete files with given extension

  "Delete files")
     read_dir
     echo "Enter file's extension:"  
     read EXT
     amount_files $DIR $EXT
     if [ $AMOUNT != "0" ];
       then
         echo "There are $AMOUNT files in directory $DIR"
         ls -p $DIR/*.$EXT
         echo "Delete them?[y/n]:"
         read CONFIRMATION
         if [ $CONFIRMATION = "y" ];
           then
             rm $DIR/*.$EXT
         fi   
       else 
         echo "Can't find files with extension $ext in directory $DIR"
     fi
  ;;
###############################
# Find in given directory the file/files witn longest name
  
  "Find file with longest name")
     read_dir
     MAX=0
     LENGTH=0
     NAMES=()

# Find max length of file's name in directory $DIR
   for F in $DIR/*.*
     do 
       LENGTH=${#F} 
       if [ $LENGTH -gt $MAX ];
         then
           MAX=$LENGTH
       fi
    done 
    if [ $MAX -eq 0 ];
      then 
        echo "Directory $DIR is empty"
      else 

# Find all files with longest name
        for F in $DIR/*.*
          do  
            LENGTH=${#F}           
            if [ $LENGTH -eq $MAX ];
              then 
                NAMES+=($F)
            fi
          done
        echo "Directory contains ${#NAMES[@]} files with longest name, 
        length is $(($MAX-${#DIR}-1)) symbols"

#print array of file's names      
      echo "${NAMES[@]}"
    fi
  ;;

#####################################
# Incorrect choice
  *) 
    echo "Incorrect choice. Please try again"
  ;;
esac
done
 
