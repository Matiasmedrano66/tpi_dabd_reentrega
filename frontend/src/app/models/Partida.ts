import { DetallePartida } from "./DetallePartida";

export class Partida{
    id: number;
    fechaPartida: String;
    puntosUsuario: number;
    puntosCroupier: number;
    partidaFinalizada: boolean;
    ganador: string;
    detallesPartida: DetallePartida[];


    constructor( id: number,
        fechaPartida: String,
        puntosUsuario: number,
        puntosCroupier: number,
        partidaFinalizada: boolean,
        ganador: string,
        detallesPartida: DetallePartida[]) {
        
        this.id = id,
        this.fechaPartida = fechaPartida,
        this.puntosUsuario = puntosUsuario,
        this.puntosCroupier = puntosCroupier,
        this.detallesPartida = detallesPartida,
        this.partidaFinalizada = partidaFinalizada,
        this.ganador = ganador
    }
}