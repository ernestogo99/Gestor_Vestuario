package org.example.service;


import org.example.dao.LookDAO;
import org.example.exceptions.ItemNaoEncontradoException;
import org.example.exceptions.LookNaoEncontradoException;
import org.example.exceptions.PersistenciaException;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.model.Look;

import java.util.List;

public class LookService {
    private final LookDAO lookDAO;
    private final ItemService itemService;


    public LookService(LookDAO lookDAO,ItemService itemService){
        this.itemService=itemService;
        this.lookDAO=lookDAO;
    }

    public LookService(){
        this.lookDAO=new LookDAO();
        this.itemService=new ItemService();
    }

    public void criarLook(Look look){
        this.lookDAO.criar(look);
    }

    public List<Look> listarLooks() throws PersistenciaException {
        return this.lookDAO.listar();
    }


    public Look obterLookPorId(String id) throws LookNaoEncontradoException {
        return this.lookDAO.obterLookPorId(id);
    }

    public void registrarUso(String id){
        Look look=this.obterLookPorId(id);
        look.registrarUso();
        this.lookDAO.salvarLook(look);

        if (look.getParteDecima() != null)
            itemService.salvarItem(look.getParteDecima());
        if (look.getParteDebaixo() != null)
            itemService.salvarItem(look.getParteDebaixo());
        if (look.getParteIntima() != null)
            itemService.salvarItem(look.getParteIntima());
        if (look.getAcessorios() != null) {
            for(Acessorio acessorio:look.getAcessorios()){
                this.itemService.salvarItem(acessorio);
            }
        }
    }


    public void excluirLook(String id) throws LookNaoEncontradoException{
        this.lookDAO.excluirLook(id);
    }

    public List<Acessorio> listarAcessorios(String id){
        Look look=this.obterLookPorId(id);
        return look.getAcessorios();
    }

    public void adicionarAcessorio(String id,Acessorio acessorio){
        Look look=this.obterLookPorId(id);
        look.getAcessorios().add(acessorio);
        this.lookDAO.salvarLook(look);
    }


    public void removerAcessorio(String id, String itemId) throws ItemNaoEncontradoException {
        Look look = this.lookDAO.obterLookPorId(id);
        List<Acessorio> acessorios = look.getAcessorios();
        boolean removido=false;


        for (Acessorio item : acessorios) {
            if(item.getId().equals(itemId)){
                acessorios.remove(item);
                removido=true;
                break;
            }
        }
        if (!removido) {
            throw new ItemNaoEncontradoException(itemId);
        }

        this.lookDAO.salvarLook(look);
    }


    public void modificarParteDeCima(String id, Item novaParteDeCima) {
        lookDAO.modificarParteDeCima(id, novaParteDeCima);
        itemService.salvarItem(novaParteDeCima);
    }

    public void modificarParteDeBaixo(String id, Item novaParteDeBaixo) {
        lookDAO.modificarParteDeBaixo(id, novaParteDeBaixo);
        itemService.salvarItem(novaParteDeBaixo);
    }

    public void modificarParteIntima(String id, Item novaParteIntima) {
        lookDAO.modificarParteIntima(id, novaParteIntima);
        itemService.salvarItem(novaParteIntima);
    }

    public void modificarLook(String id, Item parteDeCima, Item parteDeBaixo, Item parteIntima) {
        lookDAO.modificarLook(id, parteDeCima, parteDeBaixo, parteIntima);
        itemService.salvarItem(parteDeCima);
        itemService.salvarItem(parteDeBaixo);
        itemService.salvarItem(parteIntima);
    }

    public void modificarLook(String id, Item parteDeCima, Item parteDeBaixo) {
        lookDAO.modificarLook(id, parteDeCima, parteDeBaixo);
        itemService.salvarItem(parteDeCima);
        itemService.salvarItem(parteDeBaixo);
    }
}




