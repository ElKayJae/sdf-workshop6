#Server Side
mvn compile exec:java -Dexec.mainClass="vtp2022.day6.Server.ServerApp" -Dexec.args="3000 C:\Users\lowke\Desktop\NUSISS\sdf-workshop4\src\main\java\vtp2022\day4\Server\cookie_file.txt"

#Client
run mvn compile exec:java -Dexec.mainClass="vtp2022.day6.Client.ClientApp" -Dexec.args="0.0.0.0:3000"