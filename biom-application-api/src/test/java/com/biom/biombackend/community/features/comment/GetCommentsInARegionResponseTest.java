package com.biom.biombackend.community.features.comment;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GetCommentsInARegionResponseTest {
    
    
    @Test
    void test01() {
        // given
        ObjArray objArray = new ObjArray();
        // when
        System.out.println("objArray = " + objArray);
        
        // then
    }
    
    static class ObjArray {
        private String[] strings;
        private int[] integers;
        private InnerObj[] innerObjs;
    
        public ObjArray() {
            this.strings = new String[]{"AA", "bb", "CC"};
            this.integers = new int[]{11, 22, 33};
            this.innerObjs = new InnerObj[]{new InnerObj("FF"), new InnerObj("GG")};
        }
    
        @Override
        public String toString() {
            return "{\"ObjArray\":{" + "\"strings\":" + ((strings != null) ? Arrays.stream(strings).map(s -> "\"" + s + "\"").collect(Collectors.toList()) : null) + ",\"integers\":" + Arrays.toString(integers) + ",\"innerObjs\":" + Arrays.toString(innerObjs) + "}}";
        }
    
        static class InnerObj {
            private String innerString;
            private int innerInt;
            
            public InnerObj(String innerString) {
                this.innerString = innerString;
                this.innerInt = 4;
            }
    
            @Override
            public String toString() {
                return "{\"InnerObj\":{" + "" + ((innerString != null) ? ("\"innerString\":\"" + innerString + "\"") : ("\"innerString\":" + null)) + ",\"innerInt\":" + innerInt + "}}";
            }
    
        }
    }
}