package org.example.service;

import org.example.dao.LavagemDAO;
import org.example.exceptions.ItemNaoEncontradoException;
import org.example.exceptions.LavagemNaoEncontradaException;
import org.example.exceptions.PersistenciaException;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Lavagem;

import java.util.List;

public class LavagemService {
    private final LavagemDAO lavagemDAO;
    private final ItemService itemService;



    public LavagemService(LavagemDAO lavagemDAO,ItemService itemService){
        this.itemService=itemService;
        this.lavagemDAO=lavagemDAO;
    }

    public LavagemService(){
        this.lavagemDAO=new LavagemDAO();
        this.itemService=new ItemService();
    }

    public void criarLavagem(Lavagem lavagem) throws PersistenciaException{
        this.lavagemDAO.criarLavagem(lavagem);
    }

    public List<Lavagem> listarLavagens() throws PersistenciaException {
        return  this.lavagemDAO.listar();
    }

    public void adicionarItemALavagem(String id,ILavavel item) throws LavagemNaoEncontradaException {
        Lavagem lavagem=this.lavagemDAO.obterLavagemPorId(id);
        lavagem.getItems().add(item);
        this.lavagemDAO.salvarLavagem(lavagem);
    }

    public void registrarLavagems(String idLavagem) throws LavagemNaoEncontradaException {
        Lavagem lavagem = this.lavagemDAO.obterLavagemPorId(idLavagem);
        for (ILavavel item : lavagem.getItems()) {
            item.registrarLavagem();
            this.itemService.salvarItem((Item) item);
        }
        this.lavagemDAO.salvarLavagem(lavagem);
    }
    public Lavagem obterPorId(String id) throws LavagemNaoEncontradaException {
        return this.lavagemDAO.obterLavagemPorId(id);
    }



    public boolean contemItem(String idLavagem, ILavavel item) throws ItemNaoEncontradoException {
        Lavagem lavagem = this.lavagemDAO.obterLavagemPorId(idLavagem);
        return lavagem.getItems().contains(item);
    }

    public void removerItemDaLavagem(String id, String itemId) throws ItemNaoEncontradoException {
        Lavagem lavagem = this.lavagemDAO.obterLavagemPorId(id);
        List<ILavavel> items = lavagem.getItems();

        ILavavel itemParaRemover = null;

        for (ILavavel item : items) {
            if (item instanceof Item) {
                Item itemConcreto = (Item) item;
                if (itemConcreto.getId().equals(itemId)) {
                    itemParaRemover = item;
                    break;
                }
            }
        }

        if (itemParaRemover == null) {
            throw new ItemNaoEncontradoException(itemId);
        }

        items.remove(itemParaRemover);

        this.lavagemDAO.salvarLavagem(lavagem);
    }

}
