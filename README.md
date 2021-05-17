# user
Spring Boot + MySQL + MyBatis

## api for login
> 注册后返回id和username，注册需要telephone, usernname, password。    
> 注销后返回“success”或“failed”，注销需要id。    
> 登录后返回id和username，登录需要telephone, password。    
> 更新后返回id和username，更新需要id, telephone, username, password。    
> id是自动生成的UUID。
### add（注册）
http://localhost:8080/api/login/add
> need telephone, usernname, password    
> can only register with telephone not used before
***
> example    
> http://localhost:8080/login/add?telephone=1&username=a&password=a

### delete（注销）
http://localhost:8080/api/login/delbyid
> need id

### search（登录）
http://localhost:8080/api/login/querybytele
> need telephone, password
***
> example    
> http://localhost:8080/api/login/querybytele?telephone=1&password=a

### update（更新）
http://localhost:8080/api/login/updatebyid
> need id, telephone, username, password

## api for public material
> 删除后返回“success”或“failed”，删除需要pid。    
> 上传返回“success”或“failed”，上传需要原图、category。    
> 查询分为两种，一种是按类别查询，一种是按pid查询。    
> 按类别查询后返回该类别所有的pid及其缩略图。    
> 按pid查询后返回该图的原图。    
> pid是自动生成的UUID。    
### delete（删除）
http://localhost:8080/api/material/public/delbypid
> need pid

### add（上传）
http://localhost:8080/api/material/public/add
> need picture, category

### search（查询）
- 按类别查询
   - http://localhost:8080/api/material/public/querybycate
   > need category
   ***
   > example    
   > http://localhost:8080/api/material/public/querybycate?category=bird
- 按pid查询
   - http://localhost:8080/api/material/public/querybypid
   > need pid

## api for user picture
> 删除后返回“success”或“failed”，删除需要pid。    
> 保存返回“success”或“failed”，保存需要图片构成的描述性文件, thumbnail, id, pid。    
> 查询分为两种，一种是按id查询，一种是按pid查询。    
> 按id查询后返回该id下所有的pid及其缩略图。    
> 按pid查询后返回该图片构成的描述性文件。    
> pid是自动生成的UUID    
### delete（删除）
http://localhost:8080/api/picture/delbypid
> need pid

### save（保存）
http://localhost:8080/api/picture/save
> need file, thumbnail, id, pid

### search（查询）
- 按类别查询
   - http://localhost:8080/api/picture/querybyid
   > need id
   
- 按pid查询
   - http://localhost:8080/api/material/public/querybypid
   > need pid
