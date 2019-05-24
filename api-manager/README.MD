#Api design
##Method:  GET POST PUT PATCH DELETE
##URL: 
* http://xxx.com/v1/test
* http://xxx.com/v1/{{variable}}/test

##Params:
* Query Params
* Authorization
* Headers
* Body
    + none
    + form-data
    + x-www-form-urlencoded
    + raw
        - JSON(application/json)
    + binary(file)
* Pre-requestScript
    + javascript
    

##Table design
* api_collection

|id     |name       |description    |
|-------|-----------|---------------|

* api

|id     |collection_id  |name       |method     |url        |hasParams          |hasAuthorization        |hasHeaders      |hasBody     |hasPreRequestScript|
|-------|---------------|-----------|-----------|-----------|-------------------|------------------------|----------------|------------|-------------------|

* api_params

|id      |api_id      |key         |value           |description    |
|--------|------------|------------|----------------|---------------|

* api_headers

|id      |api_id      |key         |value           |description    |
|--------|------------|------------|----------------|---------------|

* api_body

|id      |api_id      |type        |content     |
|--------|------------|------------|------------|

* api_pre_request_script

|id      |api_id      |content      |
|--------|------------|-------------|

* api_env_category

|id      |name        |description  |
|--------|------------|-------------|

* api_env

|id     |category_id  |variable     |initial_value  |current_value  |
|-------|-------------|-------------|---------------|---------------|

##Sql
>>CREATE DATABASE IF NOT EXISTS api DEFAULT CHARSET utf8 COLLATE utf8_general_ci;