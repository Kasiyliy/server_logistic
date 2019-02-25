package kz.logistic.LogisticSystem.models;

import kz.logistic.LogisticSystem.models.audit.AuditModel;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Assylkhan
 * on 24.02.2019
 * @project spring-security-react-ant-design-polls-app
 */
@Entity
@Table(name = "cells")
public class Cell extends AuditModel {

    @ManyToOne(fetch = FetchType.EAGER)
    private Storage storage;

    @ManyToMany
    @JoinTable(name = "cell_categories",
            joinColumns = { @JoinColumn(name = "cell_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private Set<Category> categories;


    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }


}
