# Aerolinea _BondiJet_

A partir de 3 una interfaz, una clase de tests y una clase principal provistas por la cátedra, debimos desarrollar un proyecto que satisfaga las necesidades de una Aerolínea llamada Bondijet. Además, debimos cumplir ciertos requerimientos técnicos
tales como el correcto uso de **herencia, polimorfismo, sobreescritura, sobrecarga, interfaces, clases/métodos abstractos, iteradores, foreach y StringBuilder**

## 📋Consigna

La **aerolínea BondiJet** necesita el desarrollo de un sistema para gestionar el registro de sus
vuelos. Para ello se necesita diseñar un modelo que permita manejar eficientemente la información
de la empresa sobre sus vuelos, los destinos a los que llega. Por temas legales se requiere tener
un buen registro de los pasajeros de cada vuelo y el asiento que ocupan, excepto en sus vuelos
privados, facilitando así las operaciones diarias de la aerolínea. La aerolínea también tiene un
registro de los aeropuertos con los que opera.
Los vuelos de la aerolínea tienen, su destino, una cantidad de asientos y un total de tripulantes.
Cada vuelo también registra la hora y aeropuerto de salida, la hora y aeropuerto de llegada. Lleva
también un registro de sus pasajeros y el asiento asignado a cada uno. Se desea también
identificar la sección a la que pertenece el asiento. Podría suceder que haya asientos vendidos y
no ocupados por algún pasajero(Alguien que quiere viajar muy cómodo y puede pagar más
asientos sin usarlos).

**Vuelos**

Los vuelos que ofrece BondiJet pueden ser nacionales o internacionales. También ofrece vuelos
privados.

- Los vuelos nacionales solo son directos y ofrecen un solo refrigerio. Tienen 2 secciones para
ubicar a los pasajeros y una cantidad máxima de pasajeros en cada una.
- Los vuelos internacionales pueden llegar a destino con escalas o ser vuelo directo, y ofrecen
una cantidad de refrigerios que depende de cada vuelo. Si es con escalas interesa conocer el
destino de cada escala. Tienen 3 secciones para ubicar a los pasajeros y la cantidad máxima
de pasajeros en cada una.
- Los vuelos privados tienen solo destinos nacionales, un precio por Jet y cada Jet tiene
capacidad máxima de 15 asientos. Registran el pasajero que compra todo el vuelo (debe ser
un pasajero) y también cada uno de los pasajeros que lo acompañan. Pero la venta se realiza
a uno solo. El precio final del vuelo privado contempla la cantidad de Jets necesarios para
transportar al total de pasajeros.
- El origen y el destino de los vuelos son Aeropuertos a los que llega la Aerolínea. O sea, los que
ya tiene registrados.
- Los asientos de los vuelos públicos se considerarán numerados correlativamente empezando
con clase Turista y terminando con la clase Ejecutivo en un vuelo Nacional. Se numerarán
correlativamente empezando con clase Turista, siguiendo por clase Ejecutiva y terminando con
Primera clase en un vuelo Internacional. ACLARACION: las clases nombradas se refieren a
las secciones en que se clasifican los asientos de los vuelos.

En el caso de los Vuelos Privados:
- VenderVueloPrivado registra el Vuelo Privado y vende el Jet o los Jets necesarios para trasladar los pasajeros. Si son más de
uno, la cantidad de jets sólo se usa para calcular el costo total del viaje. El código del vuelo privado
es uno solo. Se da por supuesto que, si se necesitan más vuelos, se registra el vuelo y se conoce cuantos jets son, según la cantidad de pasajeros.
- Los acompañantes se asignan a los asientos del Jet que se numeran correlativamente y no hay secciones.
- Los vuelos privados no sirven refrigerios.

Respecto al cálculo del valor del pasaje:
- Cada vuelo público calculará el valor del pasaje considerando que cada sección tendrá un valor
distinto y se le sumará el valor de los refrigerios que se sirvan, según la clase de vuelo. Se
supone que todos los refrigerios tiene un mismo valor de costo. Al valor del pasaje se agrega
un 20% de impuestos.
- Los vuelos privados tienen un valor fijo del vuelo y se vende por vuelo completo. A ese valor se
agrega un 30% de impuestos.Se debe tener en cuenta que esto es por jet privado y según la
cantidad de pasajeros, se pueden necesitar más de uno.

**Cientes/Pasajeros:**

Cada cliente se identifica con su DNI, su nombre y su teléfono. Los pasajeros
son siempre clientes registrados por la aerolínea. Los clientes no siempre son pasajeros.


**Se espera que la empresa pueda:**

1. Crear la Aerolínea BondiJet dado su nombre de fantasía y CUIT
2. Registrar los nuevos Clientes dados su nombre, teléfono y DNI
3. Registrar un nuevo Aeropuerto con el que opera BondiJet conociendo su nombre,
provincia/departamento/estado/distrito (segun corresponda) y dirección.
4. Crear un vuelo público Nacional dados los datos necesarios. Deben volar a destinos válidos
nacionales.
5. Crear un vuelo público Internacional, dados los datos necesarios. Deben volar a destinos
válidos de otros países.
6. Registrar un vuelo privado conociendo el cliente que hace la compra y recibiendo una lista de
clientes que viajarán en el vuelo. Deben estar registrados previamente. Deben volar a destinos
válidos nacionales,
7. Consultar los asientos disponibles en un vuelo identificado por su código.
8. Vender un pasaje de un vuelo público nacional a un pasajero, conociendo el dni del pasajero,
el código de vuelo y el número del asiento. El pasajero debe ser cliente registrado.
9. Vender un pasaje de un vuelo público internacional a un pasajero, conociendo el dni del
pasajero, el código de vuelo, el número del asiento y su sección. El pásajero debe ser cliente
registrado.
10. Vender un vuelo privado dado el comprador (que es un pasajero) y una lista con los datos
de los pasajeros que viajan, los cuales deben ser clientes registrados en la aerolínea junto con
el comprador. Si los pasajeros que vuelan superan la capacidad, se crean los vuelos necesarios
para que viajen todos.
11. Consultar vuelos por origen, destino y fecha. Devuelve los vuelos cuya partida son en dicha
fecha y hasta los 7 días próximos, con igual origen y destino.
12. Cancelar un pasaje dado el pasajero, el vuelo y el asiento. Este vuelve a estar disponible
para la venta en ese vuelo. El pasajero debe ser un cliente registrado.
13. Cancelar un vuelo completo. Si es un vuelo público, se debe reprogramar automáticamente
los pasajes, manteniendo la sección del asiento. Si no puede reprogramar todos los pasajes
considerando los asientos disponibles en otros vuelos devuelve los códigos de pasajes sin
reasignar. Se debe reprogramar a vuelos con igual destino y no importa si las escalas fueran
distintas.
14. Devolver el total recaudado en pasajes a un destino en particular.
15. Devolver un texto con el formato dado con el detalle de un Vuelo en particular. El formato está
explicado en la Interfaz.
16. Devolver el total recaudado por todos los viajes a un destino dado. Debe resolverse en O(1).

## 📋Requerimientos técnicos

- Se deben utilizar donde sea conveniente las herramientas de Tecnologías Java que se vieron
en la materia. Al menos una vez deben usarse:
    - **Stringbuilder**, cuyo uso debe basarse en la necesidad de modificar el string.
    - **Iteradores y Foreach** para recorrer las colecciones de Java
- Se deberá utilizar en el desarrollo del trabajo **herencia** y **polimorfismo**, y al menos 2 de estos
conceptos: **sobreescritura, sobrecarga e interfaces**. Como también, en los casos que
corresponda, se deberá implementar **clases/métodos abstractos**.


Desde la materia se proveerá:
- Una Interfaz para que se utilice como base para la implementación de la clase principal
Aerolinea, con la explicación de cada método. **NO SE DEBE MODIFICAR.**
- Un código cliente con datos para crear los objetos, **NO SE DEBE MODIFICAR.**
- Una clase de testeo (junit). Será condición necesaria para aprobar esta parte del trabajo que
tanto el código cliente como el test se ejecuten sin errores. **NO SE DEBE MODIFICAR.**

## 💻☕Equipo de desarrollo

|Nombre|Contacto|
|----|----|
|  Dávalos, Leonardo | [GitHub](https://github.com/davaloslm), [LinkedIn](https://linkedin.com/in/leonardo-davalos) | 
|  Valdiviezo, Alan | [GitHub](https://github.com/AlanValdiviezo) | 

## 🛠Tecnologías
- Java 21
- Junit 4

