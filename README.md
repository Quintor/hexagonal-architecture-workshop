# Hexagonal Architecture Workshop
Deze repository bevat alle documentatie en code voor het kunnen geven van een workshop met als onderwerp Hexagonal
Architecture.

- [Onboarding](docs/onboarding.md)
- [Architectuur](docs/architectuur.md)

## Leerdoelen
1. Hoe kan je op een onderhoudbare manier een grote applicatie neerzetten?
2. Het explicieter toepassen van bekende concepten door te communiceren in termen van patterns (DI, IoC, etc.)

## Extra informatie
1. Voor het geven van de workshop is 1,5 uur benodigd

# De opdracht
We gaan een deel van een taxibedrijf casus uitwerken in een bestaand project in hexagonal stijl. Het doel van de architectuur is om de domeinen uitbreidbaar op te zetten terwijl het een monolitische applicatie betreft en hierbij ook framework onafhankelijk te zijn. Dit betekent dat we slim moeten omgaan met de communicatie tussen domeinen en bij de loskoppeling van de "business" aspecten van framework/data/services etc. specifieke technologie.

## Casus 'taxibedrijf Rëbu'
Het systeem omvat functionaliteiten voor klanten en planners om te komen tot bevestigde taxiritafspraken. In de gebruikersflow (zie afbeelding) kan een klant simpelweg met zijn telefoonnummer een boeking aanvragen zodat een planner hiermee aan de slag gaat. Het systeem bepaalt initieel de beschikbaarheid en de planner maakt hierop beslissingen. Kan een boeking niet direct worden vervuld, dan kan het systeem ook alternatieve opties bepalen. Lukt het echt niet, dan wordt een aanvraag geweigerd.
![Event storming](docs/even-storming-diagram.drawio.svg)

Vanuit de event storming zijn de volgende drie domeinen te onderkennen:

![Context map](docs/context-map-diagram.drawio.svg)
(vertaald: Customer, Booking, Planning)
U = upstream
D = downstream

In scope van de workshop is een gedeeltelijke realisatie van de Customer en Booking context (waarbij booking afhankelijk is van upstream Customer). We pakken hierbij de "aanvraag indienen" en "klant registreren" commands.

## Beginstaat van het project
Meegeleverd op de workshop starter branch is een Java Spring project met een geïmplementeerde Customer module en package opzet van de Booking module. Er is een endpoint beschikbaar voor het verkrijgen van alle customers en een Modulith named interface 'CustomerApi' die registratie functionaliteit beschikbaar stelt die we gaan gebruiken in de Booking module later in de workshop.  
![Beginstaat diagram](docs/beginstaat.drawio.png)

## Stap 1: introduceren van het Booking domein
We gaan beginnen met de domeinlaag van de Booking module (domain package), de packagestructuur is al aanwezig in het project en zit als volgt in elkaar:   
- Model: dient domeinmodellen te bevatten
- Port.inbound: bevat interfaces die business functionaliteit van het domein beschikbaar stellen 
- Port.outbound: bevat interfaces die tussen het domein en de buitenwereld/techniek zitten, zoals repositories 
- Service: bevat de implementatie van de business logica van het domein, is afhankelijk van de Port.outbound interfaces en implementeert de Port.inbound interfaces

a. Maak een ```Booking``` en ```BookingStatus``` klasse met properties op basis het onderstaande diagram. Maak er lombok ```@Value``` klasses van die met een builder te initialiseren zijn.  
![Booking domain model](docs/ddd-domain-model.drawio.svg)    
b. We willen uiteindelijk een boeking in een relationele database opslaan, maar in het domein willen we hier niets van weten. Er moet echter wel een interface beschikbaar komen waarop we een actie voor het opslaan van het zojuist aangemaakte booking model kunnen uitvoeren. Maak daarom een BookingRepository interface aan in de Port.outbound package met een "save" methode met een Booking als parameter en als return type. **Met uitzondering van op de model package, mag deze klasse geen enkele dependency hebben.**  
c. Hoewel het domeinmodel een ```customerId``` bevat, willen we in onze functionaliteit voor het aanmaken van een booking niet een customer id ontvangen. Het moet mogelijk zijn simpelweg een telefoonnummer op te geven naast de aanvraaginformatie. Om uiteindelijk achter het customer id te komen, gaat het systeem dit regelen in een latere stap. Maak voor nu een ```NewBookingCommand``` record aan met properties:
```asciidoc
String customerPhoneNumber,
LocalDateTime dateTime,
String fromLocation,
String toLocation,
byte numberOfPassengers
```
Het betreft een command, omdat onder andere het side effect van het aanmaken van een booking in een data store gaat hebben.  
d. Nu moeten we een business functionaliteit interface beschikbaar stellen waar de aangemaakte ```NewBookingCommand``` type als parameter in kan en een Booking als return type heeft. Maak een interface aan in de Port.inbound package genaamd ```BookingApi``` met een methode ```createBooking``` hiervoor. **Met uitzondering van op de model package, mag deze klasse geen enkele dependency (voor nu) hebben.**   
e. Er staat nu een model een command en inbound en outbound ports klaar, dan is het nu tijd om een service te maken die de boel bij elkaar knoopt. Maak in de service package een ```BookingService``` klasse aan die de ```BookingApi``` interface implementeert. Deze klasse heeft een associatie met de ```BookingRepository``` interface. In de createBooking methode moet een Booking object worden gecreëerd op basis van de informatie in de NewBookingCommand en vervolgens worden opgeslagen via de BookingRepository. Voor nu mag er nog geen logica worden toegevoegd voor het verkrijgen van een customerId, gebruik een random UUID.

**Optioneel: wat toelichting op keuzes**
- De service klasse heeft als logica nu alleen het omzetten van het command naar een Booking, maar dit is ook de plek om business logica te plaatsen. Het is dan minder toepasselijk om een mapper te maken die command naar booking omzet, ook gezien de properties niet één op één overeenkomen. Daarom maken we hier deze keuze om programmatisch NewBookingCommand om te zetten.
- In een daadwerkelijke implementatie wil je dat deze methode een transactie aftrapt. We maken de keuze om hiervoor de Spring Framework @Transactional annotaties te gebruiken. Ja, er is dan koppeling met een framework, maar we kunnen dit alsnog beperken tot een specifieke subset. Er is geen grote refactor nodig om van deze annotaties af te komen bij een framework wissel, terwijl het voordeel van transactiemanagement op deze manier groot is.

Als het goed is, ziet de status van het project er nu als volgt uit:  
![Booking domein](docs/booking-domain-packages.drawio.svg)