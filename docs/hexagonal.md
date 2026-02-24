# Hexagonal Architecture

Het toepassen van een modulaire applicatie waarbinnen een hexagonal architecture gebruikt wordt zorgt ervoor dat:

1. De applicatie 10 jaar of langer mee kan, wijzigingen moeten zo makkelijk als mogelijk zijn
2. De business logica geïsoleerd wordt, dus verandering in infrastructuur en manieren van het ontsluiten van de
   applicatie vereisen geen wijzigingen in de business logica
3. Het businessdomein expliciet duidelijk wordt, het domein is breder dan je eigen database

# layers
In een hexagonal architecture spelen twee lagen de hoofdrol. Dit zijn de 'Inside' en de 'Outside'.
De binnenkant bevat het domein en de business logica. Dit zou ook verder gescheiden kunnen worden.
De buitenkant bevat de 'Adapters', die leggen de connecties met de buitenwereld, zoals binnenkomende HTTP requests of queries naar databases.
En de binnenkant heeft geen idee hoe die buitenkant doet wat die doet.

Hieronder gaan we alle packages van het domein Booking langs om uit te leggen wat de rol is in een Hexagonal Architecture.

## booking.domain
Deze package gaat over de binnenkant, losgekoppeld van technische details zoals databases, web frameworks of externe services. Met andere woorden: als alles buiten de domeinlaag zou verdwijnen (REST API, frontend, database), zou de domeinlaag nog steeds logisch kloppen.
Hier vinden we de domeinobjecten, de business logica en de _ports_.

De business logica en de ports staan in de praktijk vaak in een aparte 'application' laag, die nog weer om het domain heen zit. Dit is geen expliciet onderdeel van Hexagonal architecture en daarom staat het hier niet in.

### booking.domain.model
Het domeinmodel is een conceptueel model van de business:
Het beschrijft domein objecten, relaties en regels.
Je kan ze vergelijken met de bekende JPA entiteiten, behalve dat deze objecten géén link hebben met externe technologieën.
Ze hebben properties en logica om zichzelf te valideren. 
Op het gebied van identity wordt er vaak met UUID's gewerkt. Dit komt doordat als je werkt met database gegenereerde ID's in het domein model, je een afhankelijkheid op een externe technologie introduceert. 

### booking.domain.port
Ports zijn contracten waarmee het domein aangeeft: "Dit is hoe ik met de buitenwereld ga praten"
De inbound ports gaan over binnenkomende communicatie, terwijl de outbound ports gaan over wat het domein bijvoorbeeld met data wil kunnen doen.
De implementatie van de inbound ports staat in booking.domain.service, terwijl implementaties van de outbound ports in booking.adapter.outbound staan.

Inbound ports zijn ook wel bekend als primary of driving ports, terwijl outbound ports ook secondary of driven ports kunnen heten.

### booking.domain.service
Hier staat dus de implementatie van onze inbound port. Inbound ports implementaties wil je volledig loskoppelen van de gebruikte externe technologieën. Deze BookingService hoort dus geen idee te hebben dat de data wordt opgeslagen in een relationele database.

Echter wordt het regelen van transacties wel significant meer werk als je ze hier helemaal buiten wilt laten, waardoor het soms schoorvoetend wordt toegestaan om @Transactional te gebruiken. Het liefst dan wel die van Jakarta Enterprise Edition, zodat je je niet aan Spring bindt.

## booking.adapter
De adapter layer/package bevat alles wat nodig is om de applicatie met de buitenwereld te verbinden, zoals:

* Database (JPA, MongoDB, SQL, NoSQL)
* Web frameworks / REST controllers
* Messaging / Event Bus / Kafka / RabbitMQ
* Externe API’s / third-party services
* Bestandssystemen, caching, logging

Zonder adapters zou je applicatie niet met databases, APIs of messaging systemen kunnen praten.

### booking.adapter.inbound.web
Deze package bevat een adapter. In dit geval een adapter om HTTP requests op te kunnen vangen. Om te kunnen praten met het domein wordt de BookingApi port uit booking.domain.port.inbound geïmporteerd.
Verder staan hier ook de DTO's.

### booking.adapter.outbound.persistence
Dit bevat de adapter die de outbound port BookingRepository uit booking.domain.port.outbound implementeert. Ook is hier een JPA entity variant van het domein object voor de Booking. Om hier tussen te mappen is er ook een MapStruct mapper gemaakt. De domain layer mag immers niets te weten komen over de technische implementatie.

### booking.adapter.outbound.manager
Deze package bevat twee opties om met een ander domein te praten. de _rest_ variant gaat er vanuit dat het andere domein een aparte applicatie draait, terwijl de variant in _spring_ er van uitgaat dat het andere domein in dezelfde applicatie zit. De laatste maakt gebruik van de CustomerApi interface die in customer.port.inbound staat, oftewel een inbound port is voor het customer domein.



