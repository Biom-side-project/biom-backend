package com.biom.biombackend.biom.data;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "korea_region_code")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KoreaRegionCode {
    
    @Id
    @Column(name = "region_code", nullable = false)
    private Long regionCode;
    
    @Column(name = "sido_code")
    private Integer sidoCode;
    
    @Column(name = "sigungu_code")
    private Integer sigunguCode;
    
    @Column(name = "eupmyeondong_code")
    private Integer eupmyeondongCode;
    
    @Column(name = "dongli_code")
    private Integer dongliCode;
    
    @Column(name = "sido_name")
    private String sidoName;
    
    @Column(name = "sigungu_name")
    private String sigunguName;
    
    @Column(name = "eupmyeondong_name")
    private String eupmyeondongName;
    
    @Column(name = "dongli_name")
    private String dongliName;
    
    @Column(name = "created_date")
    private String createdDate;
    
    
    @Override
    public String toString() {
        return "{\"KoreaRegionCode\":{" + "\"regionCode\":" + regionCode + ", \"sidoCode\":" + sidoCode + ", \"sigunguCode\":" + sigunguCode + ", \"eupmyeondongCode\":" + eupmyeondongCode + ", \"dongliCode\":" + dongliCode + ", \"sidoName\":" + ((sidoName != null) ? ("\"" + sidoName + "\"") : null) + ", \"sigunguName\":" + ((sigunguName != null) ? ("\"" + sigunguName + "\"") : null) + ", \"eupmyeondongName\":" + ((eupmyeondongName != null) ? ("\"" + eupmyeondongName + "\"") : null) + ", \"dongliName\":" + ((dongliName != null) ? ("\"" + dongliName + "\"") : null) + ", \"createdDate\":" + ((createdDate != null) ? ("\"" + createdDate + "\"") : null) + "}}";
    }
}
