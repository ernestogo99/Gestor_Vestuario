package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.exceptions.LavagemNaoEncontradaException;
import org.example.exceptions.PersistenciaException;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Item;
import org.example.model.Lavagem;
import org.example.utils.IEmprestavelAdapter;
import org.example.utils.ILavavelAdapter;
import org.example.utils.ItemAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LavagemDAO {
    private String nomeArquivo;
    private final Gson json;
    private final Type typeList=new TypeToken<List<Lavagem>>(){}.getType();

    public LavagemDAO(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Item.class,new ItemAdapter()).registerTypeAdapter(ILavavel.class,new ILavavelAdapter()).create();
    }

    public LavagemDAO(){
        this.nomeArquivo=Config.CAMINHO_LAVAGEM;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Item.class,new ItemAdapter()).registerTypeAdapter(ILavavel.class,new ILavavelAdapter())
                .registerTypeAdapter(IEmprestavel.class,new IEmprestavelAdapter()).create();
    }


    public void salvar(List<Lavagem> lavagems){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.nomeArquivo))){
            json.toJson(lavagems,this.typeList,writer);
        }catch (IOException error){
            throw new PersistenciaException("Erro ao salvar lavagem no arquivo: " + nomeArquivo, error);
        }
    }

    public void salvarLavagem(Lavagem lavagem){
       List<Lavagem> lavagems=this.listar();
       lavagems.removeIf(lavagem1 -> lavagem1.getId().equals(lavagem.getId()));
       lavagems.add(lavagem);
       this.salvar(lavagems);
    }

    public List<Lavagem> listar(){
        try(BufferedReader reader=new BufferedReader(new FileReader(this.nomeArquivo))){
            List<Lavagem> lavagems=json.fromJson(reader,this.typeList);
            if(lavagems!=null){
                return lavagems;
            }
            this.salvar(new ArrayList<Lavagem>());
            return new ArrayList<Lavagem>();
        } catch (IOException error) {
            throw new PersistenciaException("Erro ao obter lavagems do arquivo: " + nomeArquivo, error);
        }
    }


    public void criarLavagem(Lavagem lavagem){
        List<Lavagem> lavagems=this.listar();
        lavagems.add(lavagem);
        this.salvar(lavagems);
    }

    public Lavagem obterLavagemPorId(String id) {
        return this.listar().stream().filter(l -> l.getId().equals(id)).findFirst().orElseThrow(() -> new LavagemNaoEncontradaException(id));
    }

}


