@echo off
setlocal enabledelayedexpansion

:: List of values
set "list=gray blue ace agender aro aroace bi demiboy demigirl demi_r demi_s enby gay genderfluid genderqueer greyrose grey_r grey_s intersex lesbian pan poly pride trans"

:: Loop through the list and generate JSON
for %%i in (%list%) do (
    set "filename=%%i_shark.json"
    echo { > %%i_shark.json
    echo   "model": { >> %%i_shark.json
    echo     "type": "minecraft:model", >> %%i_shark.json
    echo     "model": "blahaj:block/%%i_shark" >> %%i_shark.json
    echo   } >> %%i_shark.json
    echo } >> %%i_shark.json
    echo Generated %%i_shark.json
)

pause