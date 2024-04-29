# restcountriescompose
### Requerimientos tecnicos del proyecto

- Jeckpack Compose.
- Coorutinas.
- Inyección de dependencias con Dagger Hilt.
- Flows.
- Arquitetura MVI (modelo, vista, intención).
- Bases de datos con ROOM.
- NavController, navegación en compose.
- Consumo Api Rest con Retrofit.
- ViewModel.
- Use case.

### Capas propuestas

##Core:
- Base de datos (Database): Este directorio contendría todo lo relacionado con la gestión de la base de datos, como las clases de acceso a datos, los modelos de datos, las migraciones, etc.
- Network: Aquí se ubicaría la lógica relacionada con las operaciones de red, como la configuración de las solicitudes HTTP, el manejo de respuestas, etc.
- Modelos (Models): Este directorio contendría los modelos de datos que se utilizan en la aplicación, como las clases que representan entidades de base de datos o datos recibidos desde una API.
  ##Domain:
- State: En este directorio, podrías definir los estados que puede tener tu aplicación, lo cual es especialmente útil si estás utilizando un patrón de arquitectura como MVI (Model-View-Intent).
- Use Case: Aquí se colocan los casos de uso de tu aplicación, es decir, las diferentes acciones que pueden llevarse a cabo y que encapsulan la lógica de negocio de la aplicación.
- Constantes (Constants): En este directorio podrías definir todas las constantes que necesites en tu aplicación, como URLs de la API, claves de acceso a la base de datos, códigos de error, etc.
  ##Presentación:
- Intents: Este directorio podría contener las clases que representan las intenciones del usuario en la interfaz de usuario. Dependiendo de la arquitectura utilizada, esto podría incluir clases que representan eventos de usuario (por ejemplo, hacer clic en un botón).
- UI: Aquí se encontraría la lógica relacionada con la interfaz de usuario, adaptadores de lista, etc.
- ViewModel: En este directorio podrías tener las clases ViewModel, que son responsables de manejar la lógica de presentación y de mantener el estado de la interfaz de usuario de forma independiente de la actividad o fragmento.

Esta estructura de directorios propuesta sigue los principios de separación de preocupaciones y modularidad, lo que facilita el mantenimiento y la escalabilidad del proyecto. Cada capa tiene una responsabilidad claramente definida y las interacciones entre las capas están bien organizadas, lo que promueve un diseño limpio y fácil de entender.

###Images



