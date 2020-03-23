git pull
mvn clean install
app_port=$(ps -ef | grep 'corona-0.0.1-SNAPSHOT.jar' | grep -v 'grep' | awk '{ printf $2 }')
sudo kill $app_port
echo Killed Corona_Application and Starting New
echo --------------------------------------------------------------------------------------------------------------
nohup java -jar "target/corona-0.0.1-SNAPSHOT.jar" &
