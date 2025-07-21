package org.example.controller;

import org.example.enums.Conservacao;
import org.example.exceptions.*;
import org.example.interfaces.ILavavel;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.service.ItemService;

import java.util.List;

public class ItemController {

    private final ItemService itemService;

    public ItemController() {
        this.itemService = new ItemService();
    }

    public void criarItem(Item item) throws ItemJaExisteException, CampoNuloException, PersistenciaException {
        itemService.criar(item);
    }

    public List<Item> listarItens() throws PersistenciaException {
        return itemService.listar();
    }

    public List<ILavavel> listarLavaveis() throws PersistenciaException{
        return itemService.listarItensLavaveis();
    }

    public Item buscarItem(String id) throws ItemNaoEncontradoException {
        return itemService.buscarPorId(id);
    }

    public void editarItem(String id, String cor, String tamanho, String loja, Conservacao conservacao)
            throws ItemNaoEncontradoException {
        itemService.editar(id, cor, tamanho, loja, conservacao);
    }

    public void removerItem(String id) throws ItemNaoEncontradoException {
        itemService.excluir(id);
    }

    public int exibirQuantidadeDeItens() throws PersistenciaException {
        return itemService.quantidadeDeitems();
    }

    public void registarEmprestimo(String id) throws ItemNaoEmprestavelException,ItemJaEmprestadoException {
        this.itemService.emprestarItem(id);
    }

    public void devolverItem(String id) throws ItemNaoEmprestavelException,ItemNaoEmprestadoException{
        this.itemService.devolverItem(id);
    }

    public long diasDesdeOEmprestimo(String id) throws ItemNaoEmprestadoException,ItemNaoEmprestavelException{
        return this.itemService.diasDesdeEmprestimo(id);
    }

    public List<Item> listarPartesDecima(){
        return this.itemService.listarPartesDecima();
    }

    public List<Item> listarPartesDebaixo(){
        return this.itemService.listarPartesDeBaixo();
    }

    public List<Item> listarPartesIntimas(){
        return this.itemService.listarPartesIntimas();
    }

    public List<Acessorio> listarAcessorios(){
        return this.itemService.listarAcessorios();
    }
}
