package kz.logistic.LogisticSystem.models;

import kz.logistic.LogisticSystem.models.audit.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "storages")
public class Storage extends AuditModel {

    @Column
    @NotNull
    private String name;

    public Storage() {
    }

    public Storage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}