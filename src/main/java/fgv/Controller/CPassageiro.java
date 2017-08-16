package fgv.Controller;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fgv.DAO.PassageiroDAO;
import fgv.Model.MPassageiro;

/**
 * Created by Fernando on 16/01/2017.
 */

public class CPassageiro {

    MPassageiro passageiro;
    GoogleAPI gApi;

    public CPassageiro(){
        passageiro = new MPassageiro();
        gApi = new GoogleAPI();
    }

    private void setMPassageiro(){
        if(passageiro == null)
            passageiro = new MPassageiro();
    }
    public boolean inserirPassageiro(MPassageiro p){
        return passageiro.inserirPassageiro(p);
    }

    public boolean inserirPassageiroVolley(RequestQueue rq, Context contexto, MPassageiro p){

        Map<String,String> params;
        params = new HashMap<String,String>();

        params.put("Ativo", "1");
        params.put("nome", p.getNome());
        params.put("cpf", p.getCpf());
        params.put("telefone", p.getTelefone());
        params.put("logradouro", p.getLogradouro());
        params.put("cidade", p.getCidade());
        params.put("estado", p.getEstado());
        params.put("bairro", p.getBairro());
        //params.put("idDestino", "1");//TA CHUMBADO PRA TESTE!!!!!!
        params.put("idMotorista", "1");//TA CHUMBADO PRA TESTE!!!!!!
        params.put("nomeResponsavel", p.getNomeResponsavel());
        params.put("telefoneResponsavel", p.getTelefoneResponsavel());

        LatLng latLng = gApi.identificarLocalizacao();
        params.put("latitude", String.valueOf(latLng.latitude));
        params.put("longitude", String.valueOf(latLng.longitude));

        JSONObject js = new JSONObject(params);

        params = new HashMap<String,String>();
        params.put("json", js.toString());

        String url = "https://bestrouteapi.azurewebsites.net/Api/Mobile/InserirPassageiro";
        //"http://localhost:4718/Api/Mobile/InserirPassageiro";
        return passageiro.inserirPassageiroVolley(rq, contexto, params, url);
    }

    public ArrayList<MPassageiro> selecionarPassageiros(){
        ArrayList<MPassageiro> passageiros = new ArrayList<MPassageiro>();

         return passageiros;
    }

    public MPassageiro consultarPassageiro(String nome){
        return passageiro.consultarPassageiro(nome);
    }

    /*public boolean atualizarPassageiro(MPassageiro p){
        return passageiro.atualizarPassageiro(p);
    }*/

    public void excluirPassageiro(){

    }

}
