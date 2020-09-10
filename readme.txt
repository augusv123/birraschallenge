Aplicacion desarrollada en angular (Front end) y Spring boot( Backend)

instalar dependencias de angular con "npm install"
servir front end con ng serve --o
(todo sobre la carpeta raiz "birrasFront")

para el back  correr app en un ide de java 



Para crear un usuario administrador se necesitan los siguientes datos:

URL : localhost:8080/auth/createUser

EJEMPLO JSON BODY : {"name":"administrador",
"userName": "administrador",
"email": "administrador@gmail.com",
"password": "administrador",
"roles" : ["admin"]
}


EJEMPLO RESPUESTA: 
{
    "mensaje": "Usuario creado"
}