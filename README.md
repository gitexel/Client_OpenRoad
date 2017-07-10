#Client_OpenRoad
=====
OpenRoad is a client JavaFX app for verifying and authenticating passengers to travel with virtual visa around the world, the app is very simple, it makes the customer signup, apply and search for flight trips to any country.

------
To use the project you need to put your DB info inside db.properties file inside settings dir.

------

How to use the app 
-----

* login  

![screenshot from 2017-05-30 05-04-41](https://cloud.githubusercontent.com/assets/14273726/26566696/efd2300a-44fd-11e7-9074-74826ff97c26.png)


* Signup


![screenshot from 2017-05-30 05-08-22](https://cloud.githubusercontent.com/assets/14273726/26574477/307aed1e-452a-11e7-97b2-85e45ba06a0c.png)


* Dashboard



![screenshot from 2017-05-30 05-16-32](https://cloud.githubusercontent.com/assets/14273726/26574534/601224e8-452a-11e7-9fb1-7c9f17542af6.png)






Please read these notes carefully! 
----------------------------------
1. Validation for National ID required (first, middle and last name), country, gender and birthday will be marked as valid from the database only.   
2. to book trips you should fill application and payment(only one method) tab.   
3. The payment tab is just a prototype! but you need to fill just one method.   
4. you will fill application only once until your validation status expired.   
5. application and payments inputs should be marked as valid from employer side! 
 and we don't have yet employer side app so validation for inputs will be automatically marked as valid!.   
6. you can change your profile photo after you got verified.   


To test the app you need to do these steps
-----------------------------------

Fill citizen database table to compare it later with the profile tab info 


![screenshot from 2017-04-17 02-21-08](https://cloud.githubusercontent.com/assets/14273726/26574892/cebf561c-452b-11e7-98d6-5d820affd740.png)


Note! all names should be lowercase in the citizen DB

The database diagram


![db_diagram_17-4-2017](https://cloud.githubusercontent.com/assets/14273726/26574995/30b80e86-452c-11e7-9949-ff3333fe0fa0.png)


database.sql [Data_Base_opr.zip](https://github.com/gitexel/Client_OpenRoad/files/1037678/Data_Base_opr.zip)


* after entering your national id then click check then fill your personal info. and you will git this message if the operation succeeded.


![screenshot from 2017-05-30 09-06-28](https://cloud.githubusercontent.com/assets/14273726/26575368/96fe547e-452d-11e7-95fa-7302442acf0d.png)



Summary
-------

* To verify your account your personal info should be the same in the citizen database table. 
* Then you will be able to fill payment tab and application tab.



* Payment tab(it is just a prototype but you need to fill any of the methods)

![screenshot from 2017-05-30 09-07-25](https://cloud.githubusercontent.com/assets/14273726/26575378/a3105924-452d-11e7-8955-1405b76f877d.png)


** Here is home tab after you got verified and payment filled 


![screenshot from 2017-05-30 09-08-21](https://cloud.githubusercontent.com/assets/14273726/26575443/e9a74168-452d-11e7-85f1-9fbc9fe6549b.png)


* Application tab (you need to fill it to unlock trips tab)


![screenshot from 2017-05-30 09-48-08](https://cloud.githubusercontent.com/assets/14273726/26575513/42da3f9c-452e-11e7-8d73-f5c7861b952d.png)


* after (profile verified, payment tab  and application tab filled) the home  tab will be like this  


![screenshot from 2017-05-30 09-51-58](https://cloud.githubusercontent.com/assets/14273726/26575579/83d353ee-452e-11e7-9e9a-7a0a37b79946.png)



* Trips tab (to test it you need to fill trips table with some trips)



![screenshot from 2017-05-30 10-54-53](https://cloud.githubusercontent.com/assets/14273726/26575676/d67aee40-452e-11e7-818c-d9bff01129dd.png)




after you found a trip 


![screenshot from 2017-05-30 09-59-15](https://cloud.githubusercontent.com/assets/14273726/26575687/e4abae64-452e-11e7-9ce7-3640bb96d0b3.png)


after you booked the trip the main table should update to this 



![screenshot from 2017-05-30 09-59-52](https://cloud.githubusercontent.com/assets/14273726/26575721/0a948074-452f-11e7-8721-cebf0b39288d.png)


Download the executable jar [OpenRoad.1.0.alpha.zip](https://github.com/gitexel/Client_OpenRoad/files/1134210/OpenRoad.1.0.alpha.zip)


For more info please contact me (googexel@gmail.com)
