Bygger på spring-cd-6
Lagt till FlywayDB

pom.xml: Nya dependencies: flyway-core, h2
Author.java: Lagt till lastName
SystemController.java: Lagt till /dbversion
application.yaml: Lagt till databaskonfiguration
local.logback.xml: Lagt till debugloggning av flyway
Lagt till migreringsscript *.sql i resources