package com.ec.crm.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Cleanup;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @Author: wangjin
 * @Description:
 * @Param:
 * @Return:
 */
public class JsonResolver {
    private final static int BUFFER_SIZE = 4096;
    private Gson gson;
    private static JsonResolver jsonResolver;

    private JsonResolver() {
        //根据自己需要将Json解析成的类型来创建一个Gson对象，此处的参数类型为HashMap<String, Object>
        gson = new GsonBuilder().registerTypeAdapter(new TypeToken<HashMap<String, Object>>() {
        }.getType(), new JsonDeserializer<HashMap<String, Object>>() {
            @Override
            public HashMap<String, Object> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                HashMap<String, Object> hashMap = new HashMap<>(16);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    if (entry.getValue() instanceof JsonArray) {
                        hashMap.put(entry.getKey(), entry.getValue());
                    } else if (entry.getValue() instanceof JsonObject) {
                        hashMap.put(entry.getKey(), entry.getValue().toString());
                    } else {
                        hashMap.put(entry.getKey(), entry.getValue().toString().replace("\"", ""));
                    }

                }
                return hashMap;
            }
        }).setPrettyPrinting().disableHtmlEscaping().create();
    }

    /**
     * 使用单例模式生成一个对象JsonResolver对象
     *
     * @return 返回一个JsonResolver对象
     */
    public static JsonResolver getInstance() {
        if (jsonResolver == null) {
            jsonResolver = new JsonResolver();
        }
        return jsonResolver;
    }

    /**
     * 将request对象里面的参数通过Gson转成HashMap<String, Object>
     *
     * @param request
     * @return 存放解析之后的参数的map
     */
    @SneakyThrows
    public HashMap<String, Object> request2HashMap(HttpServletRequest request) throws IOException {
        HashMap<String, Object> map;
        String requestParam = new String(inputStreamToByte(request), "UTF-8");
        map = gson.fromJson(requestParam, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

    /**
     * 将获取到的输入流转成字节数组
     *
     * @param request
     * @return
     * @throws IOException
     */
    @SneakyThrows
    private byte[] inputStreamToByte(HttpServletRequest request) throws IOException {
        @Cleanup ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = request.getInputStream().read(data, 0, BUFFER_SIZE)) != -1) {
            outStream.write(data, 0, count);
        }
        return outStream.toByteArray();
    }


    public HashMap<String, String> string2HashMap(String string) {
        HashMap<String, String> map;
        try {
            map = gson.fromJson(string, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    public void multiJsonStr2HashMap(Map map, String jsonStr) {
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);
            if (value instanceof JsonArray) {
                List list = gson.fromJson((JsonArray) value, new TypeToken<List>() {
                }.getType());
                String string = gson.toJson(list);
                map.put(key, string);
            } else {
                map.put(key, value.toString().replace("\"", ""));
            }
        }
    }

}
