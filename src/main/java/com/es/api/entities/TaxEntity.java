package com.es.api.entities;

import java.util.Objects;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "taxes")
@AttributeOverride(name = "id", column = @Column(name = "tax_id"))
public class TaxEntity extends EntityIdentifier {

    // ~ ATTRIBUTE(S) ---------------------------------------------------------

    private String rfc;

    @Column(name = "cfdi_use")
    private String cfdiUse;

    @Column(name = "tax_zip_code")
    private String taxZipCode;

    @Column(name = "fiscal_regime")
    private String fiscalRegime;

    @Column(name = "constancy_file")
    private String constancyFile;

    @Column(name = "user_id")
    private Long userId;

    // ~ CONSTRUCTORS(S) ------------------------------------------------------

    public TaxEntity() {
    }

    // ~ GETTER(S) SETTER(S) --------------------------------------------------

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCfdiUse() {
        return cfdiUse;
    }

    public void setCfdiUse(String cfdiUse) {
        this.cfdiUse = cfdiUse;
    }

    public String getTaxZipCode() {
        return taxZipCode;
    }

    public void setTaxZipCode(String taxZipCode) {
        this.taxZipCode = taxZipCode;
    }

    public String getFiscalRegime() {
        return fiscalRegime;
    }

    public void setFiscalRegime(String fiscalRegime) {
        this.fiscalRegime = fiscalRegime;
    }

    public String getConstancyFile() {
        return constancyFile;
    }

    public void setConstancyFile(String constancyFile) {
        this.constancyFile = constancyFile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
