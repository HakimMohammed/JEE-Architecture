package dynamic_instantiation.presentation;


import dynamic_instantiation.dao.IDao;
import dynamic_instantiation.metier.IMetier;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Presentation {
    public static void main(String[] args) {
        try {
            JSONObject config = new JSONObject(new String(Files.readAllBytes(Paths.get("src/main/java/dynamic_instantiation/config.json"))));

            // DAO
            Class<?> classDao = Class.forName(config.getString("dao"));
            IDao dao = (IDao) classDao.getConstructor().newInstance();

            // METIER
            Class<?> classMetier = Class.forName(config.getString("metier"));

            /*
            Instantiation via constructor
             */
            // IMetier metier = (IMetier) classMetier.getConstructor(IDao.class).newInstance(dao);

            /*
            Instantiation via setter
             */
            IMetier metier = (IMetier) classMetier.getConstructor().newInstance();
            Method setDao = classMetier.getDeclaredMethod("setDao", IDao.class);
            setDao.invoke(metier, dao);

            //Output
            System.out.println(metier.calcul());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
