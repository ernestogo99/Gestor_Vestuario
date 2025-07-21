package org.example.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.exceptions.LookNaoEncontradoException;
import org.example.exceptions.PersistenciaException;
import org.example.interfaces.IEmprestavel;
import org.example.interfaces.ILavavel;
import org.example.model.Acessorio;
import org.example.model.Item;
import org.example.model.Look;
import org.example.utils.AcessorioAdapter;
import org.example.utils.IEmprestavelAdapter;
import org.example.utils.ILavavelAdapter;
import org.example.utils.ItemAdapter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LookDAO {
    private String nomeArquivo;
    private final Gson json;
    private final Type typeList=new TypeToken<List<Look>>(){}.getType();


    public LookDAO(String nomeArquivo){
        this.nomeArquivo=nomeArquivo;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Item.class,new ItemAdapter()).registerTypeAdapter(ILavavel.class,new ILavavelAdapter())
                .registerTypeAdapter(IEmprestavel.class, new IEmprestavelAdapter())
                .registerTypeAdapter(Acessorio.class, new AcessorioAdapter()).create();
    }

    public LookDAO(){
        this.nomeArquivo=Config.CAMINHO_LOOK;
        this.json=new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Item.class,new ItemAdapter()).registerTypeAdapter(ILavavel.class,new ILavavelAdapter())
                .registerTypeAdapter(IEmprestavel.class, new IEmprestavelAdapter())
                .registerTypeAdapter(Acessorio.class, new AcessorioAdapter()).create();
    }


    public void salvar(List<Look> looks){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(this.nomeArquivo))){
            json.toJson(looks,this.typeList,writer);
        }catch (IOException error){
            throw new PersistenciaException("Erro ao salvar items no arquivo: " + nomeArquivo, error);
        }
    }

    public List<Look> listar(){
        try(BufferedReader reader=new BufferedReader(new FileReader(this.nomeArquivo))){
            List<Look> looks=json.fromJson(reader,this.typeList);
            if(looks !=null){
                return looks;
            }
            this.salvar(new ArrayList<Look>());
            return new ArrayList<Look>();
        }catch (IOException error){
            throw new PersistenciaException("Erro ao obter items no arquivo: " + nomeArquivo, error);
        }
    }


    public void salvarLook(Look look){
        List<Look> looks=this.listar();
        for(Look look1:looks){
            if(look1.getId().equals(look.getId())){
                looks.remove(look1);
                break;
            }
        }
        looks.add(look);
        this.salvar(looks);
    }

    public void criar(Look look){
        List<Look> looks=this.listar();
        looks.add(look);
        this.salvar(looks);
    }

    public Look obterLookPorId(String id) throws LookNaoEncontradoException{
        List<Look> looks=this.listar();
        for(Look look:looks){
            if(look.getId().equals(id)){
                return look;
            }
        }
        throw  new LookNaoEncontradoException(id);
    }

    public void excluirLook(String id){
        boolean removido=false;
        List<Look> looks=this.listar();
        for(Look look:looks){
            if(look.getId().equals(id)){
                looks.remove(look);
                removido=true;
                break;
            }
        }
        if(!removido){
            throw  new LookNaoEncontradoException(id);
        }
        this.salvar(looks);
    }

    public Look modificarParteDeCima(String id, Item novaParteDeCima) {
        return duplicarLookComModificacoes(id, novaParteDeCima, null, null);
    }

    public Look modificarParteDeBaixo(String id, Item novaParteDeBaixo) {
        return duplicarLookComModificacoes(id, null, novaParteDeBaixo, null);
    }

    public Look modificarParteIntima(String id, Item novaParteIntima) {
        return duplicarLookComModificacoes(id, null, null, novaParteIntima);
    }

    public Look modificarLook(String id, Item novaCima, Item novaBaixo, Item novaIntima) {
        return duplicarLookComModificacoes(id, novaCima, novaBaixo, novaIntima);
    }

    public Look modificarLook(String id,Item novaCima,Item novaBaixo){
        return duplicarLookComModificacoes(id, novaCima, novaBaixo,null);
    }

    private Look duplicarLookComModificacoes(String idOriginal, Item novaCima, Item novaBaixo, Item novaIntima) {
        Look lookOriginal = this.obterLookPorId(idOriginal);

        Look novoLook = new Look(
                novaCima != null ? novaCima : lookOriginal.getParteDecima(),
                novaBaixo != null ? novaBaixo : lookOriginal.getParteDebaixo(),
                novaIntima != null ? novaIntima : lookOriginal.getParteIntima(),
                new ArrayList<>(lookOriginal.getAcessorios())
        );

        this.criar(novoLook);
        return novoLook;
    }



}
