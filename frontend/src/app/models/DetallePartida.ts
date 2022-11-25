import { Carta } from "./Carta"

export class DetallePartida {
    id: number;
    esCartaJugador: boolean;
    carta: Carta

    constructor(id: number,
        esCartaJugador: boolean,
        carta: Carta) {
        
            this.id = id,
            this.esCartaJugador = esCartaJugador,
            this.carta = carta
    }
}