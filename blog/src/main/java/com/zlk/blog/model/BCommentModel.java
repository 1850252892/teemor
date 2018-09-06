package com.zlk.blog.model;

import com.zlk.blog.entity.BComment;

public class BCommentModel extends BComment {
    private String nickname;
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
