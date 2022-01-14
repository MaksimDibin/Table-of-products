Thesis table of products. In the application, you can log in / log in with an account. Different roles will have their own functionality available. In the product table, you can delete, modify, or add a new product only for users with the **admin** role. A table with users is available for admin, where you can perform similar manipulations as with products.
To work, you need to connect to the database. 
In the **application.properties** file, you need to change the data:
1. specify the port;
2. specify the driver;
3. change username;
4. change the password to log;
5. specify schema;
6. or hibernate, specify the dialect.

After successfully launching the application on the browser page, you must enter http://localhost:8080/product