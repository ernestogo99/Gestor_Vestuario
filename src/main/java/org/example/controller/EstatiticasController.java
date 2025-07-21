package org.example.controller;

import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Look;
import org.example.service.EstatisticasService;

import java.util.List;

public class EstatiticasController {
    private final EstatisticasService estatisticasService;


    public EstatiticasController(){
        this.estatisticasService=new EstatisticasService();
    }


    public List<Item> listarItensMaisUsados(){
        return this.estatisticasService.obterItensMaisUsados();
    }

    public List<Item> listarItensMenosUsados(){
        return this.estatisticasService.obterItensMenosUsados();
    }

    public List<ILavavel> listarItensMaisLavados(){
        return this.estatisticasService.obterItensMaislavados();
    }

    public List<ILavavel> listarItensMenosLavados(){
        return this.estatisticasService.obterItensMenoslavados();
    }

    public List<Look> listarLooksMaisUsados(){
        return this.estatisticasService.obterLooksMaisUsados();
    }


    public List<IEmprestavel> listarItensEmprestados(){
        return this.estatisticasService.obterItensEmprestados();
    }

    public int qtdDeUtilizacoesDoItem(String id){
        return this.estatisticasService.qtdDeUtilizacoesDoItem(id);
    }

    public int qtdDeUtilizacoesDoLook(String id){
        return this.estatisticasService.qtdDeUtilizacoesDoLook(id);
    }


}
