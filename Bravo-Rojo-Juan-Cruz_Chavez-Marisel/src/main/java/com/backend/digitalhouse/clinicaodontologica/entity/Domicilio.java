package com.backend.digitalhouse.clinicaodontologica.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name= "DOMICILIOS")
    public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String calle;
        private int numero;
        private String localidad;
        private String provincia;


        public Domicilio(String calle, int numero, String localidad, String provincia) {
            this.calle = calle;
            this.numero = numero;
            this.localidad = localidad;
            this.provincia = provincia;
        }

        public Domicilio() {
        }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
            return id;
        }


        public String getCalle() {
            return calle;
        }

        public void setCalle(String calle) {
            this.calle = calle;
        }

        public int getNumero() {
            return numero;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

    @Override
    public String toString() {
        return "🏠Domicilio: " +
                "id: " + id +
                ", calle: " + calle +
                ", numero: " + numero +
                ", localidad: " + localidad +
                ", provincia: " + provincia;
    }
    }

