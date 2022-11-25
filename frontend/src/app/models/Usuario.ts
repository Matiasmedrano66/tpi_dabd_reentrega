import { Partida } from "./Partida";

export class Usuario {
    id: number;
    nombreUsuario: string;
    password: string;
    ultimaPartida: Partida;


    constructor( id: number,
        nombreUsuario: string,
        password: string,
        ultimaPartida: Partida
        ) {

        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.ultimaPartida = ultimaPartida;
    }

}