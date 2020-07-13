package com.resume.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InfoForm implements Serializable {
    private static final long serialVersionUID = 4632147795640747419L;
    private String info;
}
