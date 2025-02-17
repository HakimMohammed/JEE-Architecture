package spring_xml.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Database version");
        return 24;
    }
}
