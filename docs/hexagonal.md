# Hexagonal packages/lagen
Een hexagonal architecture bestaat uit diverse lagen met elk zijn eigen verantwoordelijkheden, hieronder wordt
per laag uitgewerkt wat deze verantwoordelijkheden zijn.

## application
De laag waarin je applicatie-logica leeft, losgekoppeld van technische details zoals databases, web frameworks of externe services.

Vanuit deze laag orkestreren we use cases (bijv. “plaats bestelling”, “registreer gebruiker”). 

### application.service
De services in deze laag zijn verantwoordelijk voor:

* Aanroepen domein logica
* Aanroepen ports voor ophalen en opslaan data
* Technische transacties (JTA)

## domain
De domeinlaag bevat de businesslogica van je systeem. Het is volledig onafhankelijk van frameworks, databases, of infrastructuur. Hier leef de kern van wat je applicatie eigenlijk doet, los van hoe het wordt gepresenteerd of opgeslagen.
Met andere woorden: als alles buiten de domeinlaag zou verdwijnen (REST API, frontend, database), zou de domeinlaag nog steeds logisch kloppen.

### domain.service
Services in de domeinlaag zijn verantwoordelijk voor businesslogica die niet natuurlijk bij een enkele entity past.
Bijvoorbeeld: een PaymentService dat betalingen verwerkt tussen orders en accounts.

Kenmerken zijn:
* Ze moeten onafhankelijk zijn van infrastructuur en ook van use cases.
* Ze werken alleen met de data die aan hen wordt doorgegeven.

### domain.repository
De repository laag binnen het domein is enkel bedoeld voor het beschikbaar stellen van een Port welke door de application layer gebruikt kan worden voor ophalen en opslaan van data.

De infrastructuur laag implementeert vervolgens deze port. Het interface zegt dat het iets kan ophalen en opslaan, maar weet 
niet hoe dat gedaan wordt.

### domain.model
Het domeinmodel is een conceptueel model van de business:
Het beschrijft domein objecten, relaties en regels.

Belangrijk is dat de domain laag geen JPA entiteiten bevat, dat is namelijk een implementatie detail welke
in de infrastructuur thuis hoort.

## infrastructure
De infrastructuurlaag (of adapters layer) bevat alles wat nodig is om de applicatie met de buitenwereld te verbinden, zoals:

* Database (JPA, MongoDB, SQL, NoSQL)
* Web frameworks / REST controllers
* Messaging / Event Bus / Kafka / RabbitMQ
* Externe API’s / third-party services
* Bestandssystemen, caching, logging

## infrastructure.adapters
Een adapter is een concrete implementatie van een port naar de buitenwereld. Ze vertaalt de abstracties van de kernlaag (domain + application) naar een specifieke technologie.
Zonder adapters zou je applicatie niet met databases, APIs of messaging systemen kunnen praten.

## infrastructure.adapters.inbound
De inbound adapters (ook wel primairy genoemd) zijn de adapters voor inkomende triggers zoals een REST call of een Kafka event.
Deze adapter is verantwoordelijk voor de techniek die nodig voor het ontvangen van het bericht en de vertaling 
van de inhoud van het bericht naar het domein model.

## infrastructure.adapters.outbound
De outbound adapters (ook wel secundairy genoemd) zijn de adapters voor uitgaand verkeer zoals een REST call of interactie
met een database.

## infrastructure.adapters.outbound.persistence
De persistence layer van de outbound adapters leveren de JPA entiteiten en de implementaties van de Ports (repositories) uit de domein laag. De repositories
in deze laag implementeren het interface uit de domein laag en gebruiken vervolgens indien gewenst de SpringData repositories. 

## infrastructure.adapters.outbound.persistence.jpa
De JPA layer binnen persistence bevat de JPA entiteiten.

## infrastructure.adapters.outbound.persistence.mapper
De mapper layer binnen persistence bevat mapping logica van entiteiten van en naar het domein model.

## infrastructure.adapters.outbound.persistence.spring
De spring layer binnen persistence bevat de Spring Data repositories welke door de JpaOrderRepositories gebruikt kunnen
worden.






