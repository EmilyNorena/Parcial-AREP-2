# Parcial-AREP-2

## VIDEO funcionamiento
https://youtu.be/r-e4pnERVqw?si=j7ID8vhc2ljhBFl8

## Arquitectura

<img width="644" height="337" alt="17610689790135464473075645851434" src="https://github.com/user-attachments/assets/77c78750-6382-4305-8b7f-2f0173da1759" />

El navegador actúa como cliente, enviando solicitudes HTTP.

Estas solicitudes llegan a un Service Proxy alojado en una instancia EC2.

Este proxy funciona como un intermediario o balanceador, que recibe las peticiones del cliente y las redirige a uno de los servidores disponibles.

Existen múltiples instancias EC2 que ejecutan el servicio Math Services, encargadas de aplicar los algoritmos de búsqueda (lineal y binario) 

## Cómo desplegar en AWS

En nuestra instancia EC2 instalamos Java. Localmente empaquetamos el proyecto y usando nuestra llave pública (.pem) copiamos el archivo .jar generado en nuestra instancia, finalmente, ejecutamos dentro de nuestra instancia: java -jar app.jar

