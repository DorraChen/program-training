package com.example.fieldresolution;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fieldresolution.model.Model;
import com.example.fieldresolution.model.ModelOne;
import com.example.fieldresolution.model.ModelThree;
import com.example.fieldresolution.model.ModelTwo;
import com.google.gson.Gson;

/**
 * @author clz
 * @data 2020/10/22 13:15
 * @description
 */
public class TestFieldResolution {
    public static void main(String[] args) {
        ModelOne modelOne = new ModelOne();
        Gson gson = new Gson();
        String modelOneStr = gson.toJson(modelOne);
        ModelTwo modelTwo = gson.fromJson(modelOneStr, ModelTwo.class);
        System.out.println(modelTwo);

        ModelThree modelThree = JSONObject.parseObject(JSON.toJSONString(modelOne), ModelThree.class);
        System.out.println(modelThree);



        Model model = new Model();
        String modelStr = gson.toJson(model);
        ModelTwo modelTwo1 = gson.fromJson(modelStr, ModelTwo.class);
        System.out.println(modelTwo1);

        ModelThree modelThree1 = JSONObject.parseObject(JSON.toJSONString(model), ModelThree.class);
        System.out.println(modelThree1);
    }
}
