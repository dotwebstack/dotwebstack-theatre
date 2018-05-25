@echo off
set /p pw=Plain password: 
java -jar "target/pwencrypt-1.0.0-jar-with-dependencies.jar" "%pw%"
pause
