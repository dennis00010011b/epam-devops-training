#! bin/bash
# This script provides simple login validation using if and case statements
# I know the realization could be more simpler with for-each loop
incorrect_pass () {
echo "Incorrect password"
exit
}

incorrect_name () {
echo "Incorrect login name"
exit
}

declare -A logins
logins[0]='user1'
logins[1]='user2'
logins[2]='user3'
declare -A passwords
passwords[0]='pass1'
passwords[1]='pass2'
passwords[2]='pass3'

echo "Enter login:"
read login
if [ $login = 'exit' ];
then 
exit
elif [ $login = ${logins[0]} ] || [ $login = ${logins[1]} ] || [ $login = ${logins[2]} ];
then 
echo "Enter password:"
read passwd
else
incorrect_name
fi
case $login in
    ${logins[0]} )
      if [ $passwd != ${passwords[0]} ]; 
      then 
      incorrect_pass
      fi
    ;;
    ${logins[1]} )
      if [ $passwd != ${passwords[1]} ];
      then 
      incorrect_pass
      fi
    ;;
    ${logins[2]} )
      if [ $passwd != ${passwords[2]} ];
      then      
      incorrect_pass
      fi
    ;;
    *) 
      incorrect_name
    ;;
esac
echo "SUCCESS! You logged in."         
       
            



