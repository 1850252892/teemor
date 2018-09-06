package com.zlk.blog.mapper;

import com.zlk.blog.entity.Group;
import com.zlk.blog.model.GroupModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GroupMapper {
    int deleteByPrimaryKey(String gid);

    @Delete("delete from tb_group where gid=#{gid}")
    int deleteGroup(String gid);

    @Insert("insert into tb_group(gId,uId,gName,gLabel) values(#{gid},#{uid},#{gname},#{glabel})")
    int addGroup(Group group);

    int insert(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    @Select("select * from tb_group where uId=#{uId}")
    List<Group> selectGroupList(String uId);

    @Select("select gName from tb_group,tb_bGroup where bId=#{bId} and tb_group.bId=tb_bGroup.bId")
    String selecGroupBybId(String bId);

    @Select("SELECT g.gId,g.gName,COUNT(b.gId) bCount\n" +
            "FROM tb_bgroup b RIGHT JOIN tb_group g ON b.gId=g.gId\n" +
            "WHERE g.uId=#{uId}\n" +
            "GROUP BY gId")
    List<GroupModel> selectGroupModel(String uId);

}
