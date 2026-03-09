package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;


public class Presentation2 {
    public static void main(String[] args) throws Exception {

        // ÉTAPE 1 : Quelle est la classe DAO à utiliser ?
        // Réponse : elle est spécifiée dans config.txt
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();

        // ÉTAPE 2 : Comment créer une instance de cette classe ?
        // Réponse : en utilisant l'API Reflection
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // ÉTAPE 3 : Quelle est la classe Métier à instancier ?
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // ÉTAPE 4 : Comment lier la DAO au Métier ?
        // Réponse : par injection via la méthode setDao()
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        // ÉTAPE 5 : Quel est le résultat du calcul ?
        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}
