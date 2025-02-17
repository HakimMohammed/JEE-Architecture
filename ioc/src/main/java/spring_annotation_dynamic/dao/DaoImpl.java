package spring_annotation_dynamic.dao;

import org.springframework.stereotype.Repository;

@Repository("dao")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Database version");
        return 24;
    }
}
