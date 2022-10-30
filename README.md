# Java REST API
I am Groot! `translation: Welcome space dweller!`  
This is a Guardians of the Galaxy (GOTG) inspired REST API, with different endpoints, each made by corresponding member of GOTG. And because our guardians care about safety, each endpoint is rigorously tested with JUnit and Postman. Fasten your seatbelt and enjoy!

## Tech stack
- Java Spring Boot
- MySQL
- JUnit
- Postman

## Endpoints
- ### GET /groot  
Want to have a chat with Groot? Use this endpoint!
Provide a message to be translated in query params, else the endpoint will respond with an error.  
`/groot?message=hello`
```
{
  "received": "hello",
  "translated": "I am Groot!"
}
```
`/groot`
```
{
  "error": "I am Groot!"
}
```
- ### GET /yondu
Ever wondered how fast Yondu's arrow travels? Ask no more!  
Just provide distance and time and the endpoint will calculate the arrow's speed for you.  
`/yondu/distance=100.0&time=10.0`
```
{
  "distance": 100.0,
  "time": 10.0,
  "speed": 10.0
}
```
- ### GET /rocket
Our beloved Rocket built his own endpoint and it's filled with... guns & ammunitions. Honestly, are you surprised?  
Access `GET /rocket` to find out the ship's current cargo status  
`/rocket`
```
{
  "caliber25": 0,
  "caliber30": 0,
  "caliber50": 0,
  "shipstatus": "empty",
  "ready": false
}
```
Want to top up cargo? Use `GET /rocket/fill`  
`/rocket/fill?caliber=.50&amount=5000`
```
{
  "received": ".50",
  "amount": 5000,
  "shipstatus": "40%",
  "ready": false
}
```
