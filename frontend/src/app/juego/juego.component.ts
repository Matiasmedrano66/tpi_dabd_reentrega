import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Carta } from '../models/Carta';
import Swal from 'sweetalert2';
import { Usuario } from '../models/Usuario';
import { Partida } from '../models/Partida';
import { PartidasService } from '../partidas.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-juego',
  templateUrl: './juego.component.html',
  styleUrls: ['./juego.component.css']
})
export class JuegoComponent implements OnInit {

  @Input() usuario = {} as Usuario;
  @Output() finalizarJuego = new EventEmitter<boolean>();

  partida = {} as Partida;

  cartasJugador : Carta[] = []

  cartasCrupier : Carta[] = []

  reproduciendoMusica = true;

  musica = new Audio();

  primerPartida : boolean = false;

  constructor(private servicioPartidas : PartidasService, private router: Router) { }

  ngOnInit(): void {   
   this.musica.src = "assets/audio/casino_music.mp3";
   this.musica.load();
   this.playAudio();
   if(this.usuario.ultimaPartida != null)
      this.cargarUltimaPartida();  
    else
    {
      this.partida.partidaFinalizada = true; 
      this.primerPartida = true;
    }
      
  }

  cargarUltimaPartida(){
    this.partida = this.usuario.ultimaPartida;
    this.limpiarCampos();
    this.llenarListasCartas();
  }

  elegirCartaJugador(){
    this.servicioPartidas.pedirCarta(this.partida.id).subscribe({
     next: (resp) => {
       this.partida = resp;
       this.limpiarCampos();
       this.llenarListasCartas();
       if(this.partida.partidaFinalizada)
         this.informarGanador();
 
     },
     error: () => {
       Swal.fire({
         title: 'Error al cargar partida',
         icon: 'info',
         confirmButtonText: 'Ok',
         allowOutsideClick: false,
         confirmButtonColor: '#4E9F3D',
         background: 'dark'
       });
     }
    })
   } 

  llenarListasCartas(){
    for(let i = 0; i < this.partida.detallesPartida.length; i++)
    {
      if(this.partida.detallesPartida[i].esCartaJugador)
        this.cartasJugador.push(this.partida.detallesPartida[i].carta)
      else
        this.cartasCrupier.push(this.partida.detallesPartida[i].carta)
    }
  }

  manejarMusica(){
    if(this.reproduciendoMusica){
      this.reproduciendoMusica = false;
      this.musica.pause();
    }
    else{
      this.reproduciendoMusica = true;
      this.musica.play();
    }
  }
  
  playAudio(){
    this.musica.play();      
  }
    
  plantarse(){
   this.servicioPartidas.plantarse(this.partida.id).subscribe({
    next: (resp) => {
      if(resp)
      {
        this.limpiarCampos();
        this.partida = resp;
        this.llenarListasCartas();
        this.informarGanador();
      }
    },
    error: () => {
      Swal.fire({
        title: 'Error al cargar partida',
        icon: 'info',
        confirmButtonText: 'Ok',
        allowOutsideClick: false,
        confirmButtonColor: '#4E9F3D',
        background: 'dark'
      });
    }
   })
  }

  seguirJugando(){
    this.servicioPartidas.nuevaPartida(this.usuario.id).subscribe({
      next: (resp) =>{
          if(resp){
            this.limpiarCampos();
            this.partida = resp;
            this.llenarListasCartas();
          }
      },
      error: ()=>{
        Swal.fire({
          title: 'Error al cargar partida',
          icon: 'info',
          confirmButtonText: 'Ok',
          allowOutsideClick: false,
          confirmButtonColor: '#4E9F3D',
          background: 'dark'
        });
      }
    })
  }

  cerrarSesion(){
    Swal.fire({
      title: '¿Seguro que deseas Salir del Juego?',
      showDenyButton: true,
      confirmButtonText: 'Si',
      denyButtonText: 'No'
 
    }).then((result) => {
      if (result.isConfirmed) {
        this.reproduciendoMusica = false;
        this.musica.pause();
        this.finalizarJuego.emit(true);
      } else if (result.isDenied) {
        return;
      }
    })
  }

  informarGanador(){
    this.primerPartida = false;
    if(this.partida.ganador == "empate")
    {
      Swal.fire({
        title: 'Partida finalizada',
        text: "Ha sido un Empate",
        icon: 'info',
        confirmButtonText: 'Ok',
        allowOutsideClick: false,
        confirmButtonColor: '#4E9F3D',
        background: 'dark'
      });
    }
    if(this.partida.ganador == "usuario")
    {
      Swal.fire({
        title: 'Partida finalizada',
        text: "Ganaste!",
        icon: 'success',
        confirmButtonText: 'Ok',
        allowOutsideClick: false,
        confirmButtonColor: '#4E9F3D',
        background: 'dark'
      });

      let audio = new Audio();
      audio.src = "assets/audio/win.mp3";
      audio.load();
      audio.play();
    }
    if(this.partida.ganador == "croupier")
    {
      Swal.fire({
        title: 'Partida finalizada',
        text: "Gano el croupier",
        icon: 'error',
        confirmButtonText: 'Ok',
        allowOutsideClick: false,
        confirmButtonColor: '#4E9F3D',
        background: 'dark'
      });
      let audio = new Audio();
      audio.src = "assets/audio/loose.mp3";
      audio.load();
      audio.play();
    }
   
  }
 
  limpiarCampos(){
    this.cartasJugador = [];
      this.cartasCrupier = [];
  }


  verReportes(){
    this.router.navigateByUrl('reportes');  
  }
}
