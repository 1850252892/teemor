package com.zlk.blog.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UserPro {
        public String getUserByMap(Map<String,String> m){
            return new SQL(){
                {
                    SELECT("*");
                    FROM("tb_user");
                    if(m.get("uid")!=null)
                        WHERE("uId=#{uid}");
                    if(m.get("nickname")!=null)
                        WHERE("nickname=#{nickname}");
                    if(m.get("sex")!=null)
                        WHERE("sex=#{sex}");
                    if (m.get("email")!=null)
                        WHERE("email=#{email}");
                    if (m.get("phone")!=null)
                        WHERE("phone=#{phone}");
                }

            }.toString();
        }
}
