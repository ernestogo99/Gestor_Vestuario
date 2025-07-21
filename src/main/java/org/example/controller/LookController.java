package org.example.controller;

import org.example.exceptions.ItemNaoEncontradoException;
import org.example.exceptions.LookNaoEncontradoException;
import org.example.exceptions.PersistenciaException;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.model.Look;
import org.example.service.LookService;

import java.util.List;

public class LookController {
    private final LookService lookService;

    public LookController() {
        this.lookService = new LookService();
    }

    public List<Look> listarLooks () throws PersistenciaException {
        return this.lookService.listarLooks();
    }

    public void criarLook(Look look) {
        lookService.criarLook(look);
    }

    public Look obterLookPorId(String id) throws LookNaoEncontradoException {
        return lookService.obterLookPorId(id);
    }

    public List<Acessorio> listarAcessoriosDoLook(String id) throws LookNaoEncontradoException{
        return this.lookService.listarAcessorios(id);
    }

    public void excluirLook(String id) throws LookNaoEncontradoException{
        this.lookService.excluirLook(id);
    }

    public void registrarUso(String id) throws LookNaoEncontradoException {
        lookService.registrarUso(id);
    }

    public void adicionarAcessorio(String lookId, Acessorio acessorio) throws LookNaoEncontradoException {
        lookService.adicionarAcessorio(lookId, acessorio);
    }

    public void removerAcessorio(String lookId, String itemId) throws LookNaoEncontradoException, ItemNaoEncontradoException {
        lookService.removerAcessorio(lookId, itemId);
    }

    public void modificarParteDeCima(String lookId, Item novaParteDeCima) throws LookNaoEncontradoException {
        lookService.modificarParteDeCima(lookId, novaParteDeCima);
    }

    public void modificarParteDeBaixo(String lookId, Item novaParteDeBaixo) throws LookNaoEncontradoException {
        lookService.modificarParteDeBaixo(lookId, novaParteDeBaixo);
    }

    public void modificarParteIntima(String lookId, Item novaParteIntima) throws LookNaoEncontradoException {
        lookService.modificarParteIntima(lookId, novaParteIntima);
    }

    public void modificarLook(String lookId, Item parteDeCima, Item parteDeBaixo, Item parteIntima) throws LookNaoEncontradoException {
        lookService.modificarLook(lookId, parteDeCima, parteDeBaixo, parteIntima);
    }

    public void modificarLook(String lookId, Item parteDeCima, Item parteDeBaixo) throws LookNaoEncontradoException {
        lookService.modificarLook(lookId, parteDeCima, parteDeBaixo);
    }
}
