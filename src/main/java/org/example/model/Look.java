package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Look {
    private String id;
    private Item parteDecima;
    private Item parteDebaixo;
    private Item parteIntima;
    private List<Acessorio> acessorios;
    private int numeroDeUsos;

    public Look(Item parteDecima,Item parteDebaixo,Item parteIntima){
        this.id= UUID.randomUUID().toString();
        this.parteDecima=parteDecima;
        this.parteDebaixo=parteDebaixo;
        this.parteIntima=parteIntima;
        this.acessorios=new ArrayList<>();
    }

    public Look(Item parteDecima,Item parteDebaixo,Item parteIntima,List<Acessorio> acessorios){
        this.id= UUID.randomUUID().toString();
        this.parteDecima=parteDecima;
        this.parteDebaixo=parteDebaixo;
        this.parteIntima=parteIntima;
        this.acessorios=acessorios;
    }

    public void registrarUso() {
        this.numeroDeUsos++;

        if (parteDecima != null) parteDecima.registrarUso();
        if (parteDebaixo != null) parteDebaixo.registrarUso();
        if (parteIntima != null) parteIntima.registrarUso();
        if (acessorios != null) {
            for (Acessorio acessorio : acessorios) {
                acessorio.registrarUso();
            }
        }
    }

    public int getNumeroDeUsos(){
        return this.numeroDeUsos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getParteDecima() {
        return parteDecima;
    }

    public void setParteDecima(Item parteDecima) {
        this.parteDecima = parteDecima;
    }

    public Item getParteDebaixo() {
        return parteDebaixo;
    }

    public void setParteDebaixo(Item parteDebaixo) {
        this.parteDebaixo = parteDebaixo;
    }

    public Item getParteIntima() {
        return parteIntima;
    }

    public void setParteIntima(Item parteIntima) {
        this.parteIntima = parteIntima;
    }

    public List<Acessorio> getAcessorios() {
        return acessorios;
    }

    public void modificarLook(Item parteDecima,Item parteDebaixo,Item parteIntima){
        this.parteIntima=parteIntima;
        this.parteDebaixo=parteDebaixo;
        this.parteDecima=parteDecima;
    }

    public void modificarLook(Item parteDecima,Item parteDebaixo){
        this.parteDebaixo=parteDebaixo;
        this.parteDecima=parteDecima;
    }


    @Override
    public String toString() {
        return "Look{" +
                "id='" + id + '\'' +
                ", parteDecima=" + parteDecima +
                ", parteDebaixo=" + parteDebaixo +
                ", parteIntima=" + parteIntima +
                ", acessorios=" + acessorios +
                ", numeroDeUsos=" + numeroDeUsos +
                '}';
    }
}
