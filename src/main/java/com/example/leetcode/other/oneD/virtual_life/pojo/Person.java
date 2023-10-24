package com.example.leetcode.other.oneD.virtual_life.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author renxinheng
 * @ClassName Person
 * @createTime 2023/8/21
 * @desc
 */
@Data
public class Person {
    private String name;
    private boolean sex;
    private int age;

    private List<Family> families;
    private Map<Family, FamilyMember> familyMemberMap;
}
