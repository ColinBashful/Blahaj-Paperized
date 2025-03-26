@echo off
setlocal enabledelayedexpansion

:: List of values
set "list=ace agender aro aroace bi demiboy demigirl demi_r demi_s enby gay genderfluid genderqueer greyrose grey_r grey_s intersex lesbian pan poly pride trans"

:: Loop through the list and generate JSON
for %%i in (%list%) do (
    set "filename=%%i_shark.json"
	echo { > %%i_shark.json
	echo   "parent": "blahaj:block/shark", >> %%i_shark.json
	echo   "textures": { >> %%i_shark.json
    echo     "0": "blahaj:block/pride_sharks/%%i", >> %%i_shark.json
    echo     "particle": "blahaj:block/pride_sharks/%%i" >> %%i_shark.json
    echo   } >> %%i_shark.json
    echo } >> %%i_shark.json
    echo Generated %%i_shark.json
)

pause