package metier;

import dao.IDao;

public class MetierImpl implements IMetier {

    private IDao dao;

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
