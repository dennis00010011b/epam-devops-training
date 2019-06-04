# Script organizes files from directory specified by parameter 
# to directory ./cinderella (wiil be created if doesn't exist)
#!/bin/bash
function ensure () {
if [ ! -d $1 ]
then mkdir $1
fi
} 
d="cinderella"
ensure "$d"

ensure $d/video
ensure $d/audio
ensure $d/books
cp "$1"/*.pdf ./"$d"/books
cp "$1"/*.chm ./"$d"/books
cp "$1"/*.djvu ./"$d"/books
cp "$1"/*.mp3 ./"$d"/audio

ensure "$d"/video/80x
ensure "$d"/video/200x
ensure "$d"/video/latest


cp "$1"/*198* ./"$d"/video/80x
cp "$1"/*200* ./"$d"/video/200x
for i in 2014 2015 2016
do 
cp "$1"/*"$i"* ./"$d"/video/latest
done

