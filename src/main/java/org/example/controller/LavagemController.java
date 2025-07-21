package org.example.controller;

import org.example.exceptions.ItemNaoEncontradoException;
import org.example.exceptions.LavagemNaoEncontradaException;
import org.example.exceptions.PersistenciaException;
import org.example.interfaces.ILavavel;
import org.example.model.Lavagem;
import org.example.service.ItemService;
import org.example.service.LavagemService;

import java.util.List;

public class LavagemController {
    private final LavagemService lavagemService;


    public LavagemController() {
        this.lavagemService = new LavagemService();
    }

    public void criarLavagem(Lavagem lavagem) throws PersistenciaException {
        this.lavagemService.criarLavagem(lavagem);
    }

    public List<Lavagem> listarLavagens() throws PersistenciaException {
        return this.lavagemService.listarLavagens();
    }

    public void adicionarItemNaLavagem(String id, ILavavel item) throws LavagemNaoEncontradaException {
        this.lavagemService.adicionarItemALavagem(id, item);
    }

    public void registrarLavagem(String id) throws LavagemNaoEncontradaException {
        this.lavagemService.registrarLavagems(id);
    }

    public Lavagem obterPorId(String id) throws LavagemNaoEncontradaException {
        return this.lavagemService.obterPorId(id);
    }

    public boolean contemItem(String id, ILavavel item) throws ItemNaoEncontradoException {
        return this.lavagemService.contemItem(id, item);
    }

    public void removerItemDaLavagem(String id, String itemId) throws ItemNaoEncontradoException {
        this.lavagemService.removerItemDaLavagem(id, itemId);
    }
}
