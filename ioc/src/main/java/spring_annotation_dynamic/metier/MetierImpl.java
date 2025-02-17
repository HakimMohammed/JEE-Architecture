package spring_annotation_dynamic.metier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import spring_annotation_dynamic.dao.IDao;

@Service("metier")
@PropertySource("classpath:application.properties")
public class MetierImpl implements IMetier {

    //    @Autowired
    private IDao dao;

    @Value("${DAO_NAME}")
    private String daoClassName;

//    public MetierImpl() {
//    }

    public MetierImpl(@Qualifier("dao") IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {

        System.out.println(daoClassName);
        return dao.getData() * 23;
    }
}
