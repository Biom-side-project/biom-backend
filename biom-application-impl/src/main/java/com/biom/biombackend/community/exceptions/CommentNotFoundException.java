package com.biom.biombackend.community.exceptions;

import com.biom.biombackend.users.exceptions.ExceptionWithStatusCode;

public class CommentNotFoundException extends ExceptionWithStatusCode {
    public CommentNotFoundException() {
        super("코멘트를 찾지 못했습니다.", 404);
    }
}
