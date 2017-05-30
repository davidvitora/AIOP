/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.util;

import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;
import br.com.aiop.persistencia.entidades.timeline.TimelinePost;
import br.com.aiop.persistencia.entidades.timeline.file.TimelineFileUpload;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;


public class JsonMaker {
    
    /*Transforma uma lista de objetos de timeline genericos em JSON*/
    public static JSONObject Timeline(List<GenericTimeline> timeline){
        JSONObject obj;
        JSONArray array;
        array = new JSONArray();
        
        /*Timeline tipes */
        GenericTimeline GenericTL;
        TimelinePost PostTL;
        TimelineFileUpload fileUploadTL;
        /*----------------*/
        
        Iterator<GenericTimeline> it = timeline.iterator();
        while(it.hasNext()){
            obj = new JSONObject();
            GenericTL = it.next();
            obj.put("id", GenericTL.getId());
            obj.put("tipo", GenericTL.getType());
            obj.put("data", GenericTL.getDate());
            obj.put("usuario", GenericTL.getIdUser());
            obj.put("uuid", "000000000000000000000000000000000000");
            obj.put("limit", 0 );
            if(GenericTL.getType() == 1){
                PostTL = (TimelinePost)GenericTL;
                obj.put("conteudo", PostTL.getContent());
            }
            else if(GenericTL.getType() == 2){
                fileUploadTL = (TimelineFileUpload) GenericTL;
                obj.put("nome_do_arquivo", fileUploadTL.getFileName());
            }
            array.put(obj);
        }
        obj = new JSONObject();
        obj.put("Lista", array);
        return obj;
    }
    
}
