package org.example.service;

import org.example.exceptions.PersistenciaException;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Look;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EstatisticasService {
    private final LookService lookService;
    private final ItemService itemService;


    public EstatisticasService(){
        this.lookService=new LookService();
        this.itemService=new ItemService();

    }

    public List<Item> obterItensMaisUsados() throws PersistenciaException {
        List<Item> itens = itemService.listar();

        Collections.sort(itens, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Integer.compare(i2.getNumeroDeUsos(), i1.getNumeroDeUsos());
            }
        });

        return itens;
    }

    public List<Item> obterItensMenosUsados() throws PersistenciaException {
        List<Item> itens = itemService.listar();

        Collections.sort(itens, new Comparator<Item>() {
            @Override
            public int compare(Item i1, Item i2) {
                return Integer.compare(i1.getNumeroDeUsos(), i2.getNumeroDeUsos());
            }
        });

        return itens;
    }

    public List<ILavavel> obterItensMaislavados() throws PersistenciaException{
        List<ILavavel> lavaveis=this.itemService.listarItensLavaveis();

        Collections.sort(lavaveis, new Comparator<ILavavel>() {
            @Override
            public int compare(ILavavel o1, ILavavel o2) {
                return  Integer.compare(o2.getQuantidadeDeLavagens(),o1.getQuantidadeDeLavagens());
            }
        });
        return  lavaveis;
    }

    public List<ILavavel> obterItensMenoslavados() throws PersistenciaException{
        List<ILavavel> lavaveis=this.itemService.listarItensLavaveis();

        Collections.sort(lavaveis, new Comparator<ILavavel>() {
            @Override
            public int compare(ILavavel o1, ILavavel o2) {
                return  Integer.compare(o1.getQuantidadeDeLavagens(),o2.getQuantidadeDeLavagens());
            }
        });
        return  lavaveis;
    }

    public List<Look> obterLooksMaisUsados() throws PersistenciaException{
        List<Look> looks=this.lookService.listarLooks();

        Collections.sort(looks, new Comparator<Look>() {
            @Override
            public int compare(Look o1, Look o2) {
                return Integer.compare(o2.getNumeroDeUsos(),o1.getNumeroDeUsos());
            }
        });

        return  looks;
    }

    public List<IEmprestavel> obterItensEmprestados() {
        return this.itemService.obterItensEmEmprestimo();
    }

    public int qtdDeUtilizacoesDoItem(String id){
        Item item=this.itemService.buscarPorId(id);
        return  item.getNumeroDeUsos();
    }

    public int qtdDeUtilizacoesDoLook(String id){
        Look look=this.lookService.obterLookPorId(id);
        return look.getNumeroDeUsos();
    }
}

