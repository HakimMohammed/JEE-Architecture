package static_instantiation.presentation;

import static_instantiation.extensions.DaoImplv2;
import static_instantiation.metier.MetierImpl;

public class Presentation {

    public static void main(String[] args) {

//        DaoImpl dao = new DaoImpl();
        DaoImplv2 dao = new DaoImplv2();

//      MetierImpl metier = new MetierImpl(dao);

        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);


        System.out.println(metier.calcul());
    }



}
