package spring_annotation.extensions;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import spring_annotation.dao.IDao;

@Repository("dao2")
public class DaoImplv2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Web Service version");
        return 24;
    }
}
