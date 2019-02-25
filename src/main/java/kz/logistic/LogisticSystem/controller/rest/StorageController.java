package kz.logistic.LogisticSystem.controller.rest;

import kz.logistic.LogisticSystem.models.Storage;
import kz.logistic.LogisticSystem.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * @author Assylkhan
 * on 24.02.2019
 * @project spring-security-react-ant-design-polls-app
 */
@RestController
@RequestMapping(path = "/api")
@CrossOrigin("*")
public class StorageController {

    @Autowired
    StorageService storageService;

    @GetMapping(path = "storages/{id}", produces = "application/json")
    public ResponseEntity getById(@PathVariable Long id) {
        Storage storage = storageService.getById(id);
        return storage == null ? new ResponseEntity<Storage>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<Storage>(storage, HttpStatus.OK);
    }

    @PostMapping(path = "storages")
    public ResponseEntity add(@RequestBody @Valid Storage storage) {
        return storageService.save(storage) ? ResponseEntity.status(HttpStatus.CREATED).build()
                :  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping(path = "storages")
    public ResponseEntity save(@RequestBody @Valid Storage storage) {
        return storageService.update(storage) ? ResponseEntity.status(HttpStatus.OK).build()
                :  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @DeleteMapping(path = "storages/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return storageService.deleteById(id) ? ResponseEntity.status(HttpStatus.OK).build()
                :  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping(path = "storages")
    public ResponseEntity getAll() {
        return new ResponseEntity(storageService.getAll(), HttpStatus.OK);
    }
}
