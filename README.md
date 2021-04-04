# backendProgramming
Een project opgezet met Spring Boot, JPA, Actions en meer

Voor de postman scripts zie
https://www.getpostman.com/collections/4b20dbac6d9b25f91043

Er is een jenkins omgeving opgezet met automatic deployment op mijn eigen (prive) server, bij meer vragen hiernaar ben ik beschikbaar.
De jenkins omgeving connect niet met CodeCov en Sonarcloud om dubbelop versturen te voorkomen.

Het deployment gedeelte gaat door een simpele shell script, die de files naar de omgeving verplaatst, de oude process stopt en de nieuwe start.
image::./images/postbuild.png[Jenkins Postbuild]
![Jenkins Postbuild](https://github.com/TimovanDijk/backendProgramming/blob/main/images/afterbuild.png?raw=true)

De output is net als bij github actions te zien.
![Jenkins Log](https://github.com/TimovanDijk/backendProgramming/blob/main/images/log.png?raw=true)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=TimovanDijk_backendProgramming&metric=coverage)](https://sonarcloud.io/dashboard?id=TimovanDijk_backendProgramming)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=TimovanDijk_backendProgramming&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=TimovanDijk_backendProgramming)

[![codecov](https://codecov.io/gh/TimovanDijk/backendProgramming/branch/main/graph/badge.svg?token=NZ29X69VEP)](https://codecov.io/gh/TimovanDijk/backendProgramming)
