package com.zlk.blog.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class EssayPro {
    public String getEssayList(Map<String,String> map){
        return new SQL(){
            {

                if (map.get("date")!=null){
                    System.out.println("sdfds"+map.get("date"));
                    SELECT("a.bid bid","a.btitle btitle","a.bdata bdata","a.bdate bdate","d.browse browse","f.nickname nickname","f.uid uid");
                    FROM("tb_blog a","tb_useressay c","tb_bother d","tb_user f");
                    WHERE("a.bdate like #{date}\"%\" and a.bid=c.bid and a.bid=d.bid and  c.uid=f.uid");
                }else
                if (map.get("category")!=null){
                    SELECT("a.bid","a.btitle","a.bdata","a.bdate","d.browse","f.nickname","f.uid");
                    FROM("tb_blog a","tb_bgroup b","tb_useressay c","tb_bother d","tb_user f");
                    WHERE("b.gid=#{category} and b.bid=a.bid and a.bid=c.bid and a.bid=d.bid  and c.uid=f.uid");
                }else
                if (map.get("username")!=null){
                    SELECT("a.bid","a.btitle","a.bdata","a.bdate","d.browse","f.nickname","f.uid");
                    FROM("tb_blog a","tb_useressay c","tb_bother d","tb_user f");
                    WHERE("c.uid=#{username}  and a.bid=c.bid and a.bid=d.bid  and c.uid=f.uid");
                }
            }
        }.toString();
    }
}
