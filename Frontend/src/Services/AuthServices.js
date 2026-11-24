import { java_api } from "./Api.js";

//enviamos las credenciales al endpoint de login de Spring Boot
// se hace asincroan por el retardo al hacer la peticion por red


export async function loginRequest(email, password) {
    //lanzamos peticion HTTP a la ruta exacta del login de nuestro backend
    const response = await fetch(`${java_api}/auth/login`, {
        method: "POST",
        headers: {
            //se avisa que el contenido va en formato json
            'Content-Type': 'application/json',
        },
        //convertimos de JS a JSON
        body: JSON.stringify({ email, password })
    }

    );

    //comprobamos si al respuesta es buena o no
    if (!response.ok) {

        throw new Error("Email o contraseña incorrectos");
    }
    //se parsea la respuesta JSON 
    const data = await response.json();
    // se lo devolvemos al componente que llamó a esta función.
    return data;


}

