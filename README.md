# home-test

## Requirement
Out marketing team needs a service for searching customers are in given distance from the office location.

## Assumption 
1. GPS coordinates are used for finding the distance between customer and office location.
1. List of customers is maintained in file.
1. GPS coordinates are fixed for office location.

## How to clone
git clone https://github.com/amitjaincv/home-test.git

## How to building Application
mvn clean install -P dev

## How to running Application

java -jar -Ddistance=100 -Drange=5 target\home-test-0.0.1-SNAPSHOT.jar
Output
```
CustomerEntity(coordinate=GPSCoordinate(latitude=54.180238, longitude=-5.920898), id=17, name=Patricia Cahill)
CustomerEntity(coordinate=GPSCoordinate(latitude=53.038056, longitude=-7.653889), id=26, name=Stephen McArdle)
```

java -jar -Ddistance=100 -Drange=10 target\home-test-0.0.1-SNAPSHOT.jar
Output
```
CustomerEntity(coordinate=GPSCoordinate(latitude=54.180238, longitude=-5.920898), id=17, name=Patricia Cahill)
CustomerEntity(coordinate=GPSCoordinate(latitude=53.038056, longitude=-7.653889), id=26, name=Stephen McArdle)
CustomerEntity(coordinate=GPSCoordinate(latitude=53.807778, longitude=-7.714444), id=28, name=Charlie Halligan)
```
java -jar -Ddistance=120 -Drange=30 target\home-test-0.0.1-SNAPSHOT.jar

Output
```
CustomerEntity(coordinate=GPSCoordinate(latitude=52.2559432, longitude=-7.1048927), id=9, name=Jack Dempsey)
CustomerEntity(coordinate=GPSCoordinate(latitude=52.240382, longitude=-6.972413), id=10, name=Georgina Gallagher)
CustomerEntity(coordinate=GPSCoordinate(latitude=54.180238, longitude=-5.920898), id=17, name=Patricia Cahill)
CustomerEntity(coordinate=GPSCoordinate(latitude=53.038056, longitude=-7.653889), id=26, name=Stephen McArdle)
CustomerEntity(coordinate=GPSCoordinate(latitude=53.807778, longitude=-7.714444), id=28, name=Charlie Halligan)
```


## VM Argument Meaning
1. -Ddistance allow flexibility for searching customer from a different distance from office location
1. -Drange allows to flexibly do the plus to distance and minus to distance.
Let's understand it by some example suppose we want to invite customer within 50 km from office location then customers who are [45, ...,49, 50, 51,..., 55]km in this range should be invited. But it may be a case in next invent we may want to invite from different range [40, ..., 45, ..., 49, 50, 51,..., 60]km. So this range variable give us such flexibility.
