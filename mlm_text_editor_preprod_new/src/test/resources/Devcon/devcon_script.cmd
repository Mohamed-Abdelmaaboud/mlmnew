@echo off

setlocal enableDelayedExpansion

set disable=
set enable=

:moreOptionsOrFlags

rem
rem Options and flags start with a hyphen.
rem We assign the currently examined parameter (%1) to curArg because
rem the ~x,y construct is not possible on %n (n=0 … 9) variables
rem
	set  curArg=%1

rem
rem Assign first character to curArg1stChar
rem
	set  curArg1stChar=!curArg:~0,1!

    if [!curArg1stChar!] == [-] (

rem
rem    The argument starts with a hyphen. Now check
rem    for options or flags and assign them to their
rem    respective variables
rem

		if /i [!curArg!] == [-d]  (
			:_1
	   		if not [%2] == [] (
	   			set curArg=%2
	   			set curArg1stChar=!curArg:~0,1!
				if not [!curArg1stChar!] == [-] (
					set disable=%disable% @"*%~2*"
					shift
					goto _1
				)
			)
	   ) else if /i [!curArg!] == [-e] (
			:_2
			if not [%2] == [] (
				set curArg=%2
				set curArg1stChar=!curArg:~0,1!
				if not [!curArg1stChar!] == [-] (
					set enable=%enable% @"*%~2*"
					shift
					goto _2
				)
			)
	   )
	   shift

rem
rem    We still might have more flags or options to process.
rem    So jump back to the loop and check again
rem
		goto :moreOptionsOrFlags
	)	

if [!enable!] NEQ [] devcon enable !enable!
if [!disable!] NEQ [] devcon disable !disable!
