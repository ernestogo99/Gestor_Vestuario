package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.enums.Conservacao;
import org.example.exceptions.ItemNaoEncontradoException;
import org.example.exceptions.PersistenciaException;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.model.Lavagem;
import org.example.utils.AcessorioAdapter;
import org.example.utils.IEmprestavelAdapter;
import org.example.utils.ILavavelAdapter;
import org.example.utils.ItemAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private String nomeArquivo;
    private final Gson json;
    private final Type typeList=new TypeToken<List<Item>>(){}.getType();

    public ItemDAO(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(IEmprestavel.class, new IEmprestavelAdapter()).registerTypeAdapter(Item.class,new ItemAdapter()).create();
    }

    public ItemDAO(){
        this.nomeArquivo=Config.CAMINHO_ITEMS;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Item.class,new ItemAdapter()).registerTypeAdapter(ILavavel.class,new ILavavelAdapter())
                .registerTypeAdapter(IEmprestavel.class, new IEmprestavelAdapter())
                .registerTypeAdapter(Acessorio.class, new AcessorioAdapter()).create();
    }

    public void salvar(List<Item> items){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.nomeArquivo))){
            json.toJson(items,this.typeList,writer);
        }catch (IOException error){
            throw new PersistenciaException("Erro ao salvar items no arquivo: " + nomeArquivo, error);
        }
    }

    public void salvarItem(Item item){
        List<Item> items=this.listar();
        for(Item item1:items){
            if(item1.getId().equals(item.getId())){
                items.remove(item1);
                break;
            }
        }
        items.add(item);
        this.salvar(items);
    }

    public List<Item> listar(){
        try(BufferedReader reader=new BufferedReader(new FileReader(this.nomeArquivo))){
            List<Item> items=json.fromJson(reader,this.typeList);
            if(items!=null){
                return  items;
            }
            this.salvar(new ArrayList<Item>());
            return new ArrayList<Item>();
        }catch (IOException error){
            throw new PersistenciaException("Erro ao obter items no arquivo: " + nomeArquivo, error);
        }
    }


    public void criar(Item item){
        List<Item> items=this.listar();
        items.add(item);
        this.salvar(items);
    }


    public Item procurarPorId(String id){
        List<Item> items=this.listar();
        for(Item item:items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        throw  new ItemNaoEncontradoException(id);
    }

    public boolean existePorId(String id){
        List<Item> items=this.listar();
        for(Item item:items){
            if(item.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public void remover(String id){
        boolean removido=false;
        List<Item> items=this.listar();
        for(Item item:items){
            if(item.getId().equals(id)){
                items.remove(item);
                removido=true;
                break;
            }
        }
        if(!removido){
            throw  new ItemNaoEncontradoException(id);
        }
        this.salvar(items);
    }

    public void editar(String id, String cor, String tamanho, String loja, Conservacao conservacao) {
        List<Item> items = this.listar();
        boolean atualizado = false;

        for (Item item : items) {
            if (item.getId().equals(id)) {
                item.setCor(cor);
                item.setTamanho(tamanho);
                item.setLojaDeOrigem(loja);
                item.setConservacao(conservacao);
                atualizado = true;
                break;
            }
        }

        if (!atualizado) {
            throw new ItemNaoEncontradoException(id);
        }

        this.salvar(items);
    }




























}
