Bygger på spring-cd-4
Lagt till persistering via JPA.

Nya filer:
  AbstractEntity.java
  Author.java
  AuthorRepository.java
  GreetingRepository.java
 
Greeting.java: @Entity, lagt till Author.

GreetingController.java:
  Döpt om till /greetings
  Delat upp i två metoder: getGreetings och storeGreeting