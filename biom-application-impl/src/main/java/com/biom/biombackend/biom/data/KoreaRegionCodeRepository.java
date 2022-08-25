package com.biom.biombackend.biom.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KoreaRegionCodeRepository extends JpaRepository<KoreaRegionCode, Long>,
                                                   JpaSpecificationExecutor<KoreaRegionCode> {
    
//    KoreaRegionCode findBySidoCode(Integer sidoCode);
    KoreaRegionCode findBySidoNameAndSigunguNameAndEupmyeondongNameAndDongliName(String sidoName, String sigunguName, String eupmyeondongName, String dongliName);
    KoreaRegionCode findBySidoName(String sidoName);
}