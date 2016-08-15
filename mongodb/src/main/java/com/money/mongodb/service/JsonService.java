package com.money.mongodb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.money.common.util.SerializeHelper;
import org.bson.Document;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * [Description]
 * date: 2016/7/5
 *
 * @author maofagui
 * @version 1.0
 */
public class JsonService {
    private static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        //objectMapper.getJsonFactory().createJsonGenerator(System.out);
    }

    public static String obj2JsonSerial(List<Document> list) throws IOException {
        StringWriter str=new StringWriter();
        objectMapper.writeValue(str, SerializeHelper.serialize(list));
        return str.toString();
    }


    public static String obj2Json(List<Document> list) throws IOException {
        if(null==list) return null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"documents\":[");
        Document document=null;
        for(int i=0; i<list.size(); i++){
            if(i!=0) stringBuilder.append(",");
            document = list.get(i);
            stringBuilder.append(document.toJson());
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    //TODO delete
    public static void main(String[]args){
        List<Document> list = new ArrayList<Document>();
        list.add(new Document());
        list.add(new Document().append("adfad","adfadsfa"));
        try {
            System.out.println(JsonService.obj2Json(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
