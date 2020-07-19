package com.resume.form;

import com.resume.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LanguageForm implements Serializable {
    private static final long serialVersionUID = 7791224905534393509L;
    @Valid
    private List<Language> languages = new ArrayList<>();
}
