# Conserjería

## Descripción
Este proyecto es una aplicación web que permite a los usuarios de un edificio
o condominio, realizar solicitudes de servicios a la administración del mismo,
así como también permite a la administración llevar un control de las solicitudes
realizadas por los usuarios.

## Requerimientos

- **Lenguaje**: [Java.17](https://jdk.java.net/)
- **Build tool**: [Gradle](https://gradle.org/)
- **Librerías**:
   - [Ebean](https://ebean.io/)
   - [Lombok](https://projectlombok.org/)
   - [SQLite](https://www.sqlite.org/index.html)
   - [Javalin](https://javalin.io/)
   - [Logback](https://logback.qos.ch/)
   - [Apache Commons Lang](https://commons.apache.org/proper/commons-lang/)
   - [Google Gson](https://github.com/google/gson)
   - [Java Faker](https://github.com/DiUS/java-faker)
   - [gRPC](https://grpc.io/)
   - [gRPC-java](https://github.com/grpc/grpc-java)
   - [Protocol Buffers](https://protobuf.dev/)
- **Plugins de Gradle**:
   - [Ebean Gradle Plugin](https://github.com/ebean-orm-tools/ebean-gradle-plugin)
   - [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)
   - [Protobuf Plugin for Gradle](https://github.com/google/protobuf-gradle-plugin)
  
## Ejecución con Docker

Si se quiere ver la imagen dockerizada de la aplicación, es necesario tener 
[Docker](https://www.docker.com/products/docker-desktop/) instalado en el equipo.

Para ejecutar la aplicación con Docker, se debe seguir los siguientes pasos:

1. Clona el repositorio:

```bash
git clone https://github.com/GSGEdgardo/Conserjeria
```

2. Entra al directorio del proyecto:

```bash
cd Conserjeria
```

3. Construye la primera imagen de Docker, esta es la que contiene la 
base de datos SQLite:

```bash
docker-compose up -d
```

4. Construye la segunda imagen de Docker, esta es la que contiene la
base de datos en MariaDB:

```bash
docker-compose -f docker-compose-mariadb.yml up -d
```
Luego de seguir esos pasos, dentro de docker desktop debería poder 
ver las dos imágenes corriendo.

## Puertos y Endpoints

### API REST y gRPC

- **Puerto:** 7070 (SQLite)
- **Puerto:** 7071 (MariaDB)

### Docker

#### Contenedores

Al terminar la ejecución de la aplicación con Docker, se deberían haber creado 3 contenedores:

1. **conserjeria-web**: Contenedor de la aplicación con la base de datos SQLite.
2. **conserjeria-conserjeria**: Contenedor de la aplicación con la base de datos MariaDB.
3. **mariadb:10.5**: Contenedor con la imagen de MariaDB.
#### Endpoints de prueba (SQLite):

1. **Home:**
  - Método: `GET`
  - URL: [http://localhost:7070/](http://localhost:7070/)
  - Descripción: Página de inicio de la API REST.

2. **Listado de Personas:**
  - Método: `GET`
  - URL: [http://localhost:7070/api/personas](http://localhost:7070/api/personas)
  - Descripción: Obtiene la lista de personas.

3. **Detalle de Persona por Rut:**
  - Método: `GET`
  - URL: [http://localhost:7070/api/personas/rut/{rut}](http://localhost:7070/api/personas/rut/{rut})
  - Descripción: Obtiene los detalles de una persona específica por su Rut.


#### Puertos MariaDB

- **Aplicación con Docker:**
  - Puerto en el Host: 7071
  - Puerto en el Contenedor: 7070
  - Descripción: Este puerto se utiliza para acceder a la aplicación cuando se ejecuta dentro del contenedor Docker. Puede ser diferente del puerto de la aplicación en el host para evitar conflictos.

#### **Endpoints de prueba MariaDB:**
1. **Home:**
- Método: `GET`
- URL: [http://localhost:7071/](http://localhost:7071/)
- Descripción: Página de inicio de la API REST.

2. **Listado de Personas:**
- Método: `GET`
- URL: [http://localhost:7071/api/personas](http://localhost:7071/api/personas)
- Descripción: Obtiene la lista de personas.

3. **Detalle de Persona por Rut:**
- Método: `GET`
- URL: [http://localhost:7071/api/personas/rut/{rut}](http://localhost:7071/api/personas/rut/{rut})
- Descripción: Obtiene los detalles de una persona específica por su Rut.

### gRPC

- **Puerto:** 50123

#### Endpoint

1. **Detalle de Persona por Rut (gRPC):**
  - Método: `GET`
  - URL SQLite: [http://localhost:7070/api/grpc/personas/rut/{rut}](http://localhost:7070/api/grpc/personas/rut/{rut})
  - URL MariaDB: [http://localhost:7071/api/grpc/personas/rut/{rut}](http://localhost:7071/api/grpc/personas/rut/{rut})
  - Descripción: Obtiene los detalles de una persona específica por su Rut mediante gRPC.



