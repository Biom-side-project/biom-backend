package com.biom.biombackend.community.data;

import com.biom.biombackend.biom.data.KoreaRegionCode;
import com.biom.biombackend.users.data.BiomUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends CommunityBaseEntity{
    @Id
    @Type(type = "uuid-char")
    private UUID commentId;
    
    private String content;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_code")
    private KoreaRegionCode regionCode;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private BiomUser user;
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "commentId = " + getCommentId() + ", " + "content = " + getContent() + ", " + "createdAt = " + getCreatedAt() + ", " + "lastModifiedAt = " + getLastModifiedAt() + ")";
    }
}
