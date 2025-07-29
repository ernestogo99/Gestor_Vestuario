package org.example.service;

import org.example.dao.ItemDAO;
import org.example.enums.Conservacao;
import org.example.exceptions.*;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private final ItemDAO itemDAO;

    public ItemService(){
        this.itemDAO=new ItemDAO();
    }

    public ItemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

  public void criar(Item item) throws ItemJaExisteException,CampoNuloException{
        if(this.itemDAO.existePorId(item.getId())){
            throw new ItemJaExisteException(item.getId());
        }
      if (item.getCor() == null || item.getCor().isBlank() ||
              item.getTamanho() == null || item.getTamanho().isBlank() ||
              item.getLojaDeOrigem() == null || item.getLojaDeOrigem().isBlank() ||
              item.getConservacao() == null) {
          throw new CampoNuloException();
      }
        this.itemDAO.criar(item);
  }

  public List<Item> listar() throws PersistenciaException {
        return  this.itemDAO.listar();
  }

  public List<ILavavel> listarItensLavaveis() throws PersistenciaException{
        List<Item> itens=this.itemDAO.listar();
        return itens.stream().filter(ILavavel.class::isInstance).map(ILavavel.class::cast).toList();
  }

  public List<IEmprestavel> listarItensEmprestaveis() throws PersistenciaException{
        List<Item> itens=this.itemDAO.listar();
        return itens.stream().filter(IEmprestavel.class::isInstance).map(IEmprestavel.class::cast).toList();
    }

    public List<IEmprestavel> obterItensEmEmprestimo()throws PersistenciaException{
        return this.listarItensEmprestaveis().stream().filter(IEmprestavel::isEstaEmprestado).toList();
    }

  public void excluir(String id) throws ItemNaoEncontradoException {
        this.itemDAO.remover(id);
  }

  public Item buscarPorId(String id) throws ItemNaoEncontradoException{
        return this.itemDAO.procurarPorId(id);
  }

    public void editar(String id, String cor, String tamanho, String loja, Conservacao conservacao) throws ItemNaoEncontradoException {
        itemDAO.editar(id, cor, tamanho, loja, conservacao);
    }

    public void salvarItem(Item item){
        this.itemDAO.salvarItem(item);
    }

   public int quantidadeDeitems(){
        return this.listar().size();
   }


    public void emprestarItem(String id) throws ItemNaoEmprestavelException,ItemJaEmprestadoException  {
        Item item = itemDAO.procurarPorId(id);
        if (!(item instanceof IEmprestavel emprestavel)) {
            throw new ItemNaoEmprestavelException();
        }
        emprestavel.registrarEmprestimo();
        this.salvarItem(item);
    }

    public void devolverItem(String id) throws ItemNaoEmprestadoException, ItemNaoEmprestavelException{
        Item item = itemDAO.procurarPorId(id);
        if (!(item instanceof IEmprestavel emprestavel)) {
            throw new ItemNaoEmprestavelException();
        }
        emprestavel.registrarDevolucao();
        this.salvarItem(item);
    }

    public long diasDesdeEmprestimo(String id) throws ItemNaoEmprestavelException,ItemNaoEmprestadoException {
        Item item = itemDAO.procurarPorId(id);
        if (!(item instanceof IEmprestavel emprestavel)) {
            throw new ItemNaoEmprestavelException();
        }

        return emprestavel.quantidadeDeDiasDesdeOEmprestimo();
    }

    public List<Item> listarPartesDecima(){
        List<Item> itens=this.listar();
        return itens.stream().filter(item -> item instanceof Camisa || item instanceof Casaco).toList();
    }

    public List<Item> listarPartesDeBaixo(){
        List<Item> itens=this.listar();
        return itens.stream().filter(item->item instanceof Calca || item instanceof Saia).toList();
    }

    public List<Item> listarPartesIntimas(){
        List<Item> itens=this.listar();
        return itens.stream().filter(item-> item instanceof Cueca || item instanceof Calcinha).toList();
    }

    public List<Acessorio> listarAcessorios(){
        List<Item> itens=this.listar();
        return itens.stream().filter(Acessorio.class::isInstance).map(Acessorio.class::cast).toList();
    }
}
