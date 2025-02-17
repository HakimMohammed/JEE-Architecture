package static_instantiation.extensitons;

import static_instantiation.dao.IDao;

public class DaoImplv2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Web Service version");
        return 24;
    }
}
