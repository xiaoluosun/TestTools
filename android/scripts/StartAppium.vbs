Set ws = CreateObject("Wscript.Shell")
Dim currTime,time
currTime = now
t = replace(currTime," ","-")			'时间格式中'/',':'不符合win命名规范，需要替换。
t2 = replace(t,"/",".")
time = replace(t2,":",";")
ws.run "D:\\Appium\\node_modules\\.bin\\appium.cmd --log-level warn --log-timestamp --log D:\\Appium\\logs\\log"+time,vbhide