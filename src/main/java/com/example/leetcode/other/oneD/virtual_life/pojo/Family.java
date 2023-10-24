package com.example.leetcode.other.oneD.virtual_life.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author renxinheng
 * @ClassName Family
 * @createTime 2023/8/21
 * @desc
 */
@Data
public class Family {

    private List<FamilyMember> members;//家庭成员

    private List<FamMemberRelation> relations;//成员关系
}
