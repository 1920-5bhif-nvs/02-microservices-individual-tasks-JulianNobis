# Übung 02 - Microservices - Julian Nobis

# Fehler: Null Pointer Exception bei den Metrics. Werde das Projekt noch einmal neu machen bis 19.11.2019.<br>Bitte um Verständnis. Vielen Dank!

## Beschreibung
Ein Quarkus JPA-Projekt zur <strong>Administration von Tennisspielern</strong> in einem Tennisclub.<br>Ein Tennisspieler kann entweder ein <strong>Turnierspieler</strong> (mit einer zusätzlichen <strong>Lizenznummer</strong>), oder ein <strong>Hobbyspieler</strong> (hat eine Berechtigung für den sog. <strong>'Hobbycup'</strong>) sein. <br>Ein Tennisclub kann mehrere <strong>Teams</strong> (= Mannschaften, welche in der sog. 'Meisterschaft' offizielle Matches gegen andere Mannschaften bestreiten) bereitstellen.<br>Clubintern können die <strong>Spieler</strong> selbstverständlich auch <strong>gegeneinander spielen</strong>, diese Matches (<strong>Singles</strong> oder <strong>Doubles</strong>) werden bei den jeweiligen Spielern mitgespeichert. 

## CLD
![alt text)(CLD.png)

## Prometheus
Prometheus ist ein Open-Source-Toolkit zur <strong>Systemüberwachung</strong> von Containern und Microservices.<br>So ist es möglich, Metrics grafisch anzeigen zu lassen.<br>
Durch das Einbinden der folgenden Dependency in die pom wird der ````localhost:8181/metrics```` Endpoint bereitgestellt. 
````
<dependency>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-smallrye-metrics</artifactId>
</dependency>
````

## Istio
Istio ist ein Open-Source-Projekt, welches darauf ausgelegt ist, die <strong>Verwaltung von Service-Meshes zu vereinfachen.</strong> 
(Erklärung Service-Mesh: Mit einem Service Mesh lässt sich kontrollieren, wie unterschiedliche Teile einer Anwendung Daten miteinander teilen.)
<br>Zu den Kernfunktionen von Istio gehören das Traffic Management sowie Sicherheits-, Verbindungs- und Monitoring-Funktionen.

