package xyz.teemor.custom.custom_provider;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import xyz.teemor.custom.custom_provider.util.RedisUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class test {

    @Test
    public void tes(){
        String str = "123456";
        String salt = "";
        String md5 =new Md5Hash("123456","1008611",1).toString();
//        md5=new Md5Hash(md5).toString() ;
//        char[] mm=md5.toCharArray();
//
//        String md=new Md5Hash(mm).toString();
        System.out.println(md5);
    }
    @Test
    public void redisTest(){
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.init();
        redisUtil.set("abc","acd");
        String value=redisUtil.get("abc");
        System.out.println(value);

        Map<String,String> map=new HashMap<>();
        map.put("abc","afasd");
        map.put("adf","adf");
        redisUtil.set("map",map);
        Map<String,String> m= (Map<String, String>) redisUtil.getObject("map");
        Set<String> keys=m.keySet();
        for (String s:keys){
            System.out.println(s+":"+m.get(s));
        }
    }
}
