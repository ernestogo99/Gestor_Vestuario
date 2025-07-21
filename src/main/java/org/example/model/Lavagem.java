package org.example.model;

import org.example.interfaces.ILavavel;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Lavagem {
    private String id;
    private  List<ILavavel> items;


    public Lavagem(List<ILavavel> items){
        this.id= UUID.randomUUID().toString();
        this.items=items;
    }

    public Lavagem(){
        this.id= UUID.randomUUID().toString();
        this.items=new ArrayList<ILavavel>();
    }


    public List<ILavavel> getItems() {
        return this.items;
    }

    public int quantidadeDeitems(){
        return this.items.size();
    }

    public String getId(){
        return this.id;
    }


    @Override
    public String toString() {
        return "Lavagem{" +
                "id='" + id + '\'' +
                ", items=" + items +
                '}';
    }
}
