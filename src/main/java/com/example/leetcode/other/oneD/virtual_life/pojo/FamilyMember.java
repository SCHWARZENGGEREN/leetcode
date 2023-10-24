package com.example.leetcode.other.oneD.virtual_life.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FamilyMember {
    private Family family;
    private List<Member> members;

    private List<FamMemberRelation> relations;//成员关系
    private Map<Member, Family> memberMap;//属于的家庭以及在家庭的地位


    public static enum Member{
        Dad,
        Mom,
        Son,
        Daughter,

        Husband,
        Wife,

        ElBrother,
        YoBrother,
        ElSister,
        YoSister
    }
}