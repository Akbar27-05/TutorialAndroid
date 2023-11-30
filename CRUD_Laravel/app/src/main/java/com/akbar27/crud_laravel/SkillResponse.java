package com.akbar27.crud_laravel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SkillResponse {
    @SerializedName("data")
    private List<Skill> skills;

    public SkillResponse(List<Skill> skills) {
        this.skills = skills;
    }

    public List<Skill> getSkills() {
        return skills;
    }
}
