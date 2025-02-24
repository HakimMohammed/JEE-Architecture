package presentation;

import dao.IDao;
import metier.IMetier;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PresDynamic {
    public static void main(String[] args) {
        try {
            JSONObject config = new JSONObject(new String(Files.readAllBytes(Paths.get("src/main/resources/config.json"))));

            Class<?> classDao = Class.forName(config.getString("dao"));
            IDao dao = (IDao) classDao.getDeclaredConstructor().newInstance();

            Class<?> classMetier = Class.forName(config.getString("metier"));
            IMetier metier = (IMetier) classMetier.getDeclaredConstructor().newInstance();
            Method setDao = classMetier.getMethod("setDao", IDao.class);
            setDao.invoke(metier, dao);

            System.out.println(metier.calcul());
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
