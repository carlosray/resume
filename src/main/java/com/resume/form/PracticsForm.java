package com.resume.form;

import com.resume.entity.Practic;
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
public class PracticsForm implements Serializable {
    private static final long serialVersionUID = -7208009387584425006L;
    @Valid
    private List<Practic> practics = new ArrayList<>();
}
