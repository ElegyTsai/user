# user
Spring Boot + MySQL + MyBatis

## operation on login
### add example
http://localhost:8080/login/add?telephone=1&username=a&password=a  
(can only register with telephone not used before)

### delete example
http://localhost:8080/login/delbytele?telephone=1

### search example
http://localhost:8080/login/querybytele?telephone=1

### update example
http://localhost:8080/login/updatebytele?telephone=1&username=b&password=c

## operation on public material
### delete example
http://localhost:8080/material/public/delbypid?pid=1

### search example
http://localhost:8080/material/public/querybycate?category=bird
