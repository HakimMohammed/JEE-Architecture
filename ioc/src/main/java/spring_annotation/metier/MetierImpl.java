package spring_annotation.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring_annotation.dao.IDao;

@Service("metier")
public class MetierImpl implements IMetier {

    //    @Autowired
    private IDao dao;

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
        return dao.getData() * 23;
    }
}
