@ECHO OFF
cls
setlocal enableDelayedExpansion

set current_location=%cd%\AutomatedClient.jar
set java_location="%JAVA_PATH%"

cd /D %java_location% 
start java.exe -jar %current_location%
cls

