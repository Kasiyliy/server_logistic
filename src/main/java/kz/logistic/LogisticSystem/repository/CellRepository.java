package kz.logistic.LogisticSystem.repository;

import kz.logistic.LogisticSystem.models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 24.02.2019
 * @project spring-security-react-ant-design-polls-app
 */
@Repository
public interface CellRepository  extends JpaRepository<Cell, Long> {
}
