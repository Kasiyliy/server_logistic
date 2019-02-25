package kz.logistic.LogisticSystem.service;

import kz.logistic.LogisticSystem.models.Storage;
import kz.logistic.LogisticSystem.repository.StorageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 24.02.2019
 * @project spring-security-react-ant-design-polls-app
 */
@Service
public class StorageService {

    @Autowired
    StorageRepository storageRepository;

    private SessionFactory hibernateFactory;

    @Autowired
    public StorageService(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    public boolean save(Storage storage){
        boolean saved = false;
        if(storage.getId()==null){
            storageRepository.save(storage);
            saved = true;
        }
        return saved;
    }

    public boolean update(Storage storage){
        boolean updated = false;
        if(storage.getId()!=null){
            storageRepository.save(storage);
            updated = true;
        }
        return updated;
    }

    public boolean delete(Storage storage){
        boolean deleted = false;
        if(storage.getId()!=null){
            storage.setDeletedAt(new Date());
            storageRepository.save(storage);
            deleted = true;
        }
        return  deleted;
    }

    public boolean deleteById(Long id){
        boolean deleted = false;

        Storage storage = getById(id);
        if(storage!=null){
            delete(storage);
            deleted = true;
        }
        return  deleted;
    }

    public List<Storage> getAllWithTrashed(){
        return storageRepository.findAll();
    }

    public Storage getByIdWithTrashed(Long id){
        Optional<Storage> storageOptional = storageRepository.findById(id);
        if(storageOptional.isPresent()){
            return  storageOptional.get();
        }else{
            return null;
        }
    }

    public List<Storage> getAll(){
        Session session = hibernateFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Storage> criteriaQuery = criteriaBuilder.createQuery(Storage.class);
        Root<Storage> root = criteriaQuery.from(Storage.class);

        Predicate predicate = criteriaBuilder.isNull(root.get("deletedAt"));
        criteriaQuery.where(predicate);
        List<Storage> storages = session.createQuery(criteriaQuery).list();
        session.close();

        return  storages;
    }

    public Storage getById(Long id){
        Session session = hibernateFactory.openSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Storage> criteriaQuery = criteriaBuilder.createQuery(Storage.class);
        Root<Storage> root = criteriaQuery.from(Storage.class);

        Predicate predicateFirst = criteriaBuilder.isNull(root.get("deletedAt"));
        Predicate predicateSecond = criteriaBuilder.equal(root.get("id") , id);
        Predicate andPredicate = criteriaBuilder.and(predicateFirst , predicateSecond);
        criteriaQuery.where(andPredicate);

        Storage storage = session.createQuery(criteriaQuery).uniqueResult();
        session.close();
        return  storage;
    }

    public boolean realDeleteById(Long id){
        boolean deleted = false;
        Storage storage = getByIdWithTrashed(id);
        if(storage.getId()!=null){
            storageRepository.delete(storage);
            deleted = true;
        }
        return  deleted;
    }

    public boolean realDelete(Storage storage){
        boolean deleted = false;
        if(storage.getId()!=null){
            storageRepository.delete(storage);
            deleted = true;
        }
        return  deleted;
    }


}
