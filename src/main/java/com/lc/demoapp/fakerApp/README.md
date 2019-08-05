Java-faker

Brings the popular ruby faker gem to Java

View project on

GitHub

 Java Faker
 
Maven Status Build Status Coverage Status Dependency Status License


This library is a port of Ruby's stympy/faker gem (as well as Perl's Data::Faker library) that generates fake data. It's useful when you're developing a new project and need some pretty data for showcase.


 Usage
 
In pom.xml, add following between <dependencies> ... </dependencies>


<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>0.12</version>
</dependency>

For gradle users, add the following to your build.gradle file.


repositories {
    mavenCentral()
}


dependencies {
    testCompile group: 'com.github.javafaker', name: 'javafaker', version: '0.12'
}

In your Java code

``` java
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

``` 
 
Javadoc

http://dius.github.io/java-faker/apidocs/index.html


 Fakers
 
Address
App
Beer
Book
Bool
Business
ChuckNorris
Code
Color
Commerce
Company
Crypto
DateAndTime
Educator
Finance
Hacker
IdNumber
Internet
Lorem
Name
Number
Options
PhoneNumber
Shakespeare
Superhero
Team
University

 Usage with Locales
 
Faker faker = new Faker(new Locale("{YOUR_LOCALE}"));
 
 Supported Locales
 
ca
ca-CAT
da-DK
de
de-AT
de-CH
en
en-AU
en-au-ocker
en-BORK
en-CA
en-GB
en-IND
en-NEP
en-NZ
en-SG
en-UG
en-US
es
fa
fi-FI
fr
he
it
ja
ko
nb-NO
nl
pl
pt
pt-BR
ru
sk
sv
uk
vi
zh-CN
zh-TW

 TODO
Port more classes over as there are more entries in the yml file that we don't have classes for

 LICENSE
Copyright (c) 2014 DiUS Computing Pty Ltd. See the LICENSE file for license rights and limitations