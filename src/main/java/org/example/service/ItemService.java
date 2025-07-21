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
        List<ILavavel> lavaveis=new ArrayList<ILavavel>();
        for(Item item:itens){
            if(item instanceof ILavavel){
                lavaveis.add((ILavavel) item);
            }
        }
        return lavaveis;
  }


    public List<IEmprestavel> listarItensEmprestaveis() throws PersistenciaException{
        List<Item> itens=this.itemDAO.listar();
        List<IEmprestavel> emprestaveis=new ArrayList<IEmprestavel>();
        for(Item item:itens){
            if(item instanceof IEmprestavel){
                emprestaveis.add((IEmprestavel) item);
            }
        }
        return emprestaveis;
    }

    public List<IEmprestavel> obterItensEmEmprestimo()throws PersistenciaException{
        List<IEmprestavel> emprestaveis=this.listarItensEmprestaveis();
        List<IEmprestavel> emprestados=new ArrayList<IEmprestavel>();
        for(IEmprestavel item:emprestaveis){
            if(item.isEstaEmprestado()){
                emprestados.add(item);
            }
        }
        return emprestados;
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
        List<Item> partesDecima=new ArrayList<>();
        List<Item> itens=this.listar();
        for(Item item:itens){
            if(item instanceof Camisa || item instanceof Casaco){
                partesDecima.add(item);
            }
        }
        return partesDecima;
    }

    public List<Item> listarPartesDeBaixo(){
        List<Item> partesDeBaixo=new ArrayList<>();
        List<Item> itens=this.listar();
        for(Item item:itens){
            if(item instanceof Calca || item instanceof Saia){
                partesDeBaixo.add(item);
            }
        }
        return partesDeBaixo;
    }

    public List<Item> listarPartesIntimas(){
        List<Item> partesIntimas=new ArrayList<>();
        List<Item> itens=this.listar();
        for(Item item:itens){
            if(item instanceof Calcinha || item instanceof Cueca){
                partesIntimas.add(item);
            }
        }
        return partesIntimas;
    }

    public List<Acessorio> listarAcessorios(){
        List<Acessorio>  acessorios=new ArrayList<>();
        List<Item> itens=this.listar();
        for(Item item:itens){
            if(item instanceof Acessorio){
                acessorios.add((Acessorio) item);
            }
        }
        return acessorios;
    }
}
