package com.zlk.blog.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class BotherPro {

    public String update(Map<String,Integer> map){
            return new SQL(){
                {
                    UPDATE("tb_bother");
                    if (map.get("collect")!=null)
                        SET("collect=#{collect}");
                    if (map.get("great")!=null)
                        SET("great=#{great}");
                    if (map.get("diss")!=null)
                        SET("diss=#{diss}");
                    if (map.get("browse")!=null)
                        SET("browse=#{browse}");
                    WHERE("bId=#{bid}");
                }
            }.toString();
    }
}
