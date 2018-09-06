package xyz.teemor.blog.blog.entity;

public class UserRole {
    private Integer id;
    private String name;
    private Integer descrip;

    public UserRole() {
    }

    public UserRole(Integer id, String name, Integer descrip) {
        this.id = id;
        this.name = name;
        this.descrip = descrip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDescrip() {
        return descrip;
    }

    public void setDescrip(Integer descrip) {
        this.descrip = descrip;
    }

    @Override
    public String toString() {
        return "UserRole[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descrip=" + descrip +
                ']';
    }
}
