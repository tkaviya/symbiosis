//package net.blaklizt.symbiosis.sym_persistence.helper;
//
//import net.blaklizt.symbiosis.sym_common.configuration.Configuration;
//import net.blaklizt.symbiosis.sym_persistence.dao.super_class.AbstractDao;
//import org.hibernate.criterion.Restrictions;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.logging.Logger;
//
///**
// * Created by tkaviya on 9/13/2015.
// */
//
//public class TypeHelper {
//
//    private static Logger logger = Configuration.getNewLogger(TypeHelper.class.getSimpleName());
//
//    //buffered enums from the database DaoName -> Hashmap(description, primaryKey)
//    private static HashMap<String, HashMap<String, Long>> descriptionMap = new HashMap<>();
//
//    //map of entity classes to the getPrimaryKey methods
//    private static HashMap<String, String> primaryKeyMap = new HashMap<>();
//
//    //only needs static access
//    private TypeHelper() {}
//
//    public static void registerHelper(Class entityClass, String primaryKeyMethod) {
//        primaryKeyMap.put(entityClass.getClass().getSimpleName(), primaryKeyMethod);
//        logger.info(String.format("Registered new %s helper to invoke primary key method %s",
//            entityClass.getClass().getSimpleName(), primaryKeyMethod));
//    }
//
//    public static <E extends AbstractDao> Long getIdByDescription(E daoClass, Enum descriptionEnum)
//    {
//        String description = descriptionEnum.name();
//        logger.info(String.format("Getting %s with DAO %s", description, daoClass.getClass().getSimpleName()));
//        //check if we have already buffered this data
//        String daoClassName = daoClass.getClass().getSimpleName();
//
//        if (descriptionMap.get(daoClassName) == null) {
//            descriptionMap.put(daoClassName, new HashMap<String, Long>());
//        }
//
//        if (descriptionMap.get(daoClassName).get(description) != null) {
//            return descriptionMap.get(daoClassName).get(description);
//        }
//
//        Long primaryKey;
//        Object entity = getEntityByDescription(daoClass, description);
//        logger.info(String.format("Found %s from description %s", daoClassName, description));
//
//        try { primaryKey = (Long)entity.getClass().getMethod(primaryKeyMap.get(entity.getClass().getSimpleName())).invoke(entity); }
//        catch (Exception ex) {
//            return null;
//        }
//
//        logger.info(String.format("Found primary key %s from description %s", primaryKey, description));
//        descriptionMap.get(daoClassName).put(description, primaryKey);
//        return primaryKey;
//    }
//
//    private static <E extends AbstractDao> Object getEntityByDescription(E daoClass, String description) {
//        List result = daoClass.findByCriterion(Restrictions.eq("description", description));
//        if (result == null || result.size() != 1) return null;
//        return result.get(0);
//    }
//}
