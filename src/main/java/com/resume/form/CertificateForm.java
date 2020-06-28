package com.resume.form;

import com.resume.entity.Certificate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CertificateForm implements Serializable {
    private static final long serialVersionUID = 1911801467541425167L;

    private List<Certificate> certificates = new ArrayList<>();
}
