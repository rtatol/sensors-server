Back-end application for collecting data from sensors (https://github.com/rtatol/sensors-client) and saving them to influxdb.

[![Build Status](https://travis-ci.org/rtatol/sensors-server.svg?branch=master)](https://travis-ci.org/rtatol/sensors-server)

###### QuickStart
```
mvn clean package
java -jar target/sensors-server.jar
```

###### Sample request

POST /
```javascript
{
   "deviceId":"test_device",
   "gauges":{
      "humidity":30.00,
      "temperature":20.91,
      "pressure":997.6924,
      "lux":8.0
   }
}
```