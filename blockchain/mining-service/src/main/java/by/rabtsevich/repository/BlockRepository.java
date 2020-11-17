package by.rabtsevich.repository;

import by.rabtsevich.pojo.Block;
import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<Block, Long> {
    Block findFirstByTimestamp();
}
