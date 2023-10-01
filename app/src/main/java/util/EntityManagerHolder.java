package util;

import de.dhbwka.swe.utils.util.CommonEntityManager;

public class EntityManagerHolder {
    private static EntityManagerHolder INSTANCE;
    private final CommonEntityManager commonEntityManager = new CommonEntityManager();

    private EntityManagerHolder() {
    }

    public static EntityManagerHolder getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new EntityManagerHolder();
        }

        return INSTANCE;
    }

    public CommonEntityManager getEntityManager(){
        return commonEntityManager;
    }
}
