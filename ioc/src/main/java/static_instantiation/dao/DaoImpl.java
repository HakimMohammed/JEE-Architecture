package static_instantiation.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Database version");
        return 24;
    }
}
