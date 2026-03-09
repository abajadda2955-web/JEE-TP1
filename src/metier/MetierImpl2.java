package metier;

import dao.IDao;

public class MetierImpl2 {
    private IDao dao;

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    public double calcul() {
        return dao.getValue() * 2;
    }
}
