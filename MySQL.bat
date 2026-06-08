@echo off
COLOR F
echo __________________________Portable MySQL Server__________________________
echo ______________________________MySQL 8.4.2________________________________
echo.
echo MySQL is currently running. Please only close this window for shutdown.
echo After your server is shut off, press CTRL C to shut down this service.
echo __________________________________________________________________________
mysql\bin\mysqld --standalone --console --max_allowed_packet=128M --datadir="%~dp0mysql\data" --basedir="%~dp0mysql"

if errorlevel 1 goto error
goto finish

:error
echo.
echo MySQL could not be started.
pause

:finish
