set projectLocation=C:\Users\HP\selenium-workspace\SalesForce
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testngXMLfiles\testng_Accounts.xml
pause