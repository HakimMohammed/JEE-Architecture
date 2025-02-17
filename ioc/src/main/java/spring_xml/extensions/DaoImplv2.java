package spring_xml.extensions;

import spring_xml.dao.IDao;

public class DaoImplv2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Web Service version");
        return 24;
    }
}
