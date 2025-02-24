# Dependency Injection Part 1

This activity is an example of dependency injection using different architectures.

## Overview

- Static Instantiation:
<br> Where we have to use the keyword 'new' inside our presentation layer to create an instance of the class we want to use.
- Dynamic Injection:
<br> Where we will use either a file to store the names and path of class to initialise then call them. This way we respect the OCP principle.
- Spring XML
<br> Where we will use the Spring framework to inject the dependencies for us. By creating a config.xml file where we will define our beans and their dependencies. To be called in the presentation layer.
- Spring Annotations
<br> Where we will use the Spring framework to inject the dependencies for us. By using annotations to define the beans and their dependencies. To be called in the presentation layer.

## Code Review

In this section we will review the re used code between ll architectures, in case of any changes I will mention it.

### Dao

#### Dao Interface

A simple interface with a function getData that returns a double.

```java
package dao;

public interface IDao {
    double getData();
}
```

#### Dao Interface Implementation

an implementation of interface IDao where we override the function getData to return a double.

```java
package dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        return 10.5;
    }
}

```

### Metier
#### Metier Interface

A simple interface with a function calcul that returns a double.

```java
package metier;

public interface IMetier {
    double calcul();
}
```

#### Metier Interface Implementation

an implementation of interface IMetier where we defined the relation between Dao and Metier by Weak Coupling. We also defined two constructors one with parameter to inject the dependency and the other without parameter. The later will use the function setDao to inject the dependency.

```java
package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
    
    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
```

## Static Initialization

In this architecture we will use the keyword 'new' to create an instance of the class we want to use.

```java
package presentation;

import dao.DaoImpl;
import metier.MetierImpl;

public class PresStatique {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);

        System.out.println(metier.calcul());
    }
}
```

## Dynamic Injection

In this architecture we will use a file to store the names and path of class to initialise then call them. This way we respect the OCP principle.
In my case I used a .json file to store the names and path of the classes. That requires adding a dependency to the project.

### pom.xml
```xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20231013</version>
</dependency>
```

### config.json
```json
{
  "dao": "dao.DaoImpl",
  "metier": "metier.MetierImpl"
}
```

### PresDynamic.java

```java
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
```

## Spring XML

In this architecture we will use the Spring framework to inject the dependencies for us. By creating a config.xml file where we will define our beans and their dependencies. To be called in the presentation layer.

### pom.xml
```xml
<dependency>     
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>6.2.3</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>6.2.3</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-beans</artifactId>
    <version>6.2.3</version>
</dependency>
```

### config.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="dao" class="dao.DaoImpl"></bean>
    <bean id="metier" class="metier.MetierImpl">
        <constructor-arg ref="dao"></constructor-arg>
    </bean>
</beans>
```

### PresSpringXML.java

```java
package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresSpringXML {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        IMetier metier = (IMetier) context.getBean("metier");
        System.out.println(metier.calcul());
    }
}
```

## Spring Annotations

In this architecture we will use the Spring framework to inject the dependencies for us. By using annotations to define the beans and their dependencies. To be called in the presentation layer.
This Architecture will require adding annotation in our source code.

### DaoImpl.java

```java
package dao;

import org.springframework.stereotype.Repository;

@Repository("dao")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        return 10.5;
    }
}
```

### MetierImpl.java

```java
package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("metier")
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2;
    }
}
```

### PresSpringAnnotations.java

```java
package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresSpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("dao", "metier");
        IMetier metier = context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
```

