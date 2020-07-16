package com.resume.form;

import com.resume.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillForm  implements Serializable {
    private static final long serialVersionUID = -1285264338478903345L;
    @Valid
    private List<Skill> skills = new ArrayList<>();

}
