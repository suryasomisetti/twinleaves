
1. POST api: to create some couple of rows in all tables  
        Create Product: http://localhost:8080/api/products  
        Create Batch: http://localhost:8080/api/batches  
        Create Gtin: http://localhost:8080/api/gtins  
3. GET apis: to query based on gtins  
     http://localhost:8080/api/gtins/{gtinId}  
     http://localhost:8080/api/gtins/search/start  
     http://localhost:8080/api/gtins/search/end  
     http://localhost:8080/api/gtins/search/contains  
4. Create an api based on below conditions  
     a. To get all the gtins containing batches with positive available quantity  
         http://localhost:8080/api/gtins/quantity/positive  
     b. For batches containing negative or zero available quantity, I want just
        latest batch (based on inwardedOn filter)  
         http://localhost:8080/api/batches/latest_negative_or_zero_quantity  
-------------------------------------------------------------------------------------------------  

1. Also created some other API for other Controllers, Please go through to http://localhost:8080/swagger-ui/index.html for API Documentation  
2. Please use http://localhost:8080/h2-console for H2 Database  
   with URL: jdbc:h2:file:./data/twinleavesdb  
   User Name: twinleaves  
   password: twinleaves    
