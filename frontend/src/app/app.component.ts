import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { Usuario } from './models/Usuario';
import { UsuarioService } from './usuario.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'blackjack';

  comenzar : boolean = false;
  inicio : boolean = true;
  usuario = {} as Usuario; 
  formularioInicioSesion = {} as FormGroup 


  constructor(private servicioUsuario: UsuarioService, private fb : FormBuilder) {
    
    this.formularioInicioSesion = this.fb.group({
      nombreUsuario : ['', Validators.required],
      password: ['', Validators.required]
    })
    
  }

   public get nombreUsuario(){
    return this.formularioInicioSesion.get('nombreUsuario');
  }

  public get password(){
    return this.formularioInicioSesion.get('password');
  }

  comenzarJuego(){
    this.usuario = this.formularioInicioSesion.value;
      this.servicioUsuario.validarUsuario(this.usuario).subscribe(
        {
          next: (resp) => {
            if(resp){
              this.usuario = resp;
              this.comenzar = true;
              this.inicio = false;
            }
          },
          error: (e: HttpErrorResponse) => {
            if(e.status == 0)
            {
              Swal.fire({
                title: 'Error!',
                text: "Error de comunicación con el servidor",
                icon: 'error',
                allowOutsideClick: false,
                confirmButtonColor: '#4E9F3D'
              });
            }
            if(e.status == 401){
              Swal.fire({
                title: 'Error!',
                text: "Contraseña Incorrecta",
                icon: 'error',
                allowOutsideClick: false,
                confirmButtonColor: '#4E9F3D'
              });
            }
            if(e.status == 404){
              Swal.fire({
                title: 'Error!',
                text: "Usuario Inexistente",
                icon: 'error',
                allowOutsideClick: false,
                confirmButtonColor: '#4E9F3D'
              });
            }
          }
        }
        )
    
    }
   
  finalizar(event : boolean){
    this.comenzar = false;
    this.inicio = true;
  }
}
