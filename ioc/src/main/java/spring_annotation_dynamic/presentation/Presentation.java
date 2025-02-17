package spring_annotation_dynamic.presentation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_annotation_dynamic.config.AppConfig;
import spring_annotation_dynamic.metier.IMetier;

public class Presentation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        IMetier metier = (IMetier) context.getBean("metier");
        IMetier metier = context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
