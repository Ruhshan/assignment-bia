#Instruction for running and testing the project

##Run the project
### 1. Clone the repo
```shell
git clone https://github.com/Ruhshan/assignment-bia.git
```
### 2. Navigate to code directory
```shell
cd assignment-bia
```
### 3. Build the image and run the container with docker compose
```shell
docker-compose up
```

##Test the project
###Swagger-UI
After the images are built and containers are up navigate to [http://localhost:8100/v3/swagger-ui](http://localhost:8100/v3/swagger-ui) to access the Swagger-UI.
It has the exposed endpoints rendered for quick and easy testing.

###cURL
Example cURL requests are as follows:
#### 1. List all accessionIds
**Request**
```shell
curl -X GET "http://localhost:8100/images" -H  "accept: */*"
```
**Response**
```json
[
  "BIA-01",
  "BIA-02",
  "BIA-03",
  "BIA-04"
]
```
####2. Get all attributes associated with an accessionID
**Request**
```shell
curl -X GET "http://localhost:8100/accessions/BIA-01/metadata" -H  "accept: */*"
```
**Response**
```json
{
  "accessionID": "BIA-01",
  "author": "A. Allan",
  "species": "Arabidopsis Thaliana",
  "tissue": "Root",
  "voxel_size_bytes": 8,
  "dimensions": [
    100,
    100,
    50
  ]
}
```
####3. Get the image size in bytes of an accession
**Request**
```shell
curl -X GET "http://localhost:8100/accessions/BIA-01/imagesize" -H  "accept: */*"
```
**Response**
```json
4000000
```
***Note that, the application will initalize with the test data from this [file](https://github.com/Ruhshan/assignment-bia/blob/master/src/main/resources/testData.json). To add more data use the following POST method***

####4. Add StoredImage data
**Request**
```shell
curl -X POST "http://localhost:8100/images" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"accessionID\":\"BIA-05\",\"author\":\"Watson & Crick\",\"species\":\"Homo sapiens\",\"tissue\":\"blood\",\"voxel_size_bytes\":10,\"dimensions\":[1,1,2,3]}"
```
```json
OK
```


