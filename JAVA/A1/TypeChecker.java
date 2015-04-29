import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TypeChecker {
    /**
     * Controls whether stack traces are printed for missing
     * methods and fields.
     */
    private static boolean VERBOSE = false;
    
    /**
     * Creates a List out of items.
     * @param items
     * @return List of items
     */
    public static <T> List<T> listify(T ... items) {
        List<T> list = new ArrayList<T>();
        for (T item : items) {
            list.add(item);
        }
        return list;
    }
    
    /**
     * @param args
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws ClassNotFoundException {        
        printMissing("Could not find abstract class %s.",
                     verifyClasses(false, true, "a1soln.User"));
        
        printMissing("Could not find class %s.",
                     verifyClasses(false, false, "a1soln.Owner",
                        "a1soln.Reviewer", "a1soln.Restaurant",
                        "a1soln.LicensedRestaurant",
                        "a1soln.TakeoutRestaurant"));
        
        printMissing("Could not find interface %s.", 
                     verifyClasses(true, false, "a1soln.Takeout"));

        printMissing("Could not find %s with correct signature.",
             verifySignatures(
                        verifySignature("a1soln.User", "protected", false,
                                String.class, "username", ""),
                        verifySignature("a1soln.User", "protected", false,
                                String.class, "email", ""),   
                        verifySignature("a1soln.User", "public", false, null,
                                null, String.class, String.class),
                        verifySignature("a1soln.User", "public", false,
                                String.class, "getUsername"),
                        verifySignature("a1soln.User", "public", false,
                                String.class, "getEmail"),
                        verifySignature("a1soln.User", "public", false, void.class,
                                "setEmail", String.class),

                        // Generics aren't supported by type checker/reflection
                        verifySignature("a1soln.Owner", "private", false, 
                                "java.util.Map", "restaurants", ""),
                        verifySignature("a1soln.Owner", "public", false, null,
                                null, String.class, String.class),
                        verifySignature("a1soln.Owner", "public", false, 
                                void.class, "addRestaurant",
                                "a1soln.Restaurant"),
                        verifySignature("a1soln.Owner", "public", false,
                                boolean.class, "owns", String.class),
                        verifySignature("a1soln.Owner", "public", false,
                                void.class, "respondToReview",
                                int.class, String.class, String.class),
                        verifySignature("a1soln.Owner", "public", false, 
                                "a1soln.Restaurant", "getRestaurant",
                                String.class),
                        verifySignature("a1soln.Reviewer", "public", false, null,
                                null, String.class, String.class),
                        verifySignature("a1soln.Reviewer", "public", false, 
                                void.class, "writeReview", 
                                 "a1soln.Restaurant", boolean.class,
                                 String.class),
                        verifySignature("a1soln.Review", "private", false,
                                boolean.class, "thumbsUp", ""),
                        verifySignature("a1soln.Review", "private", false, 
                                "a1soln.Reviewer", "reviewer", ""),
                        verifySignature("a1soln.Review", "private", false, 
                                String.class, "comments", ""),
                        verifySignature("a1soln.Review", "private", false, 
                                String.class, "response", ""),
                        verifySignature("a1soln.Review", "private", false,
                                int.class, "reviewID", ""),
                        verifySignature("a1soln.Review", "private", true, 
                                int.class, "numReviews", ""),
                        verifySignature("a1soln.Review", "public", false,
                                null, null, "a1soln.Reviewer", 
                                boolean.class, String.class),
                        verifySignature("a1soln.Review", "public", false,
                                boolean.class, "getThumbsUp"),
                        verifySignature("a1soln.Review", "public", false, 
                                "a1soln.Reviewer", "getReviewer"),
                        verifySignature("a1soln.Review", "public", false,
                                String.class, "getComments"),
                        verifySignature("a1soln.Review", "public", false, 
                                String.class, "getResponse"),
                        verifySignature("a1soln.Review", "public", false,
                                int.class, "getReviewID"),
                        verifySignature("a1soln.Review", "public", false,
                                void.class, "setResponse",
                                String.class),
                        verifySignature("a1soln.Restaurant", "protected",
                                false, String.class, "name", ""),
                        verifySignature("a1soln.Restaurant", "protected",
                                false, String.class, "neighbourhood",
                                ""),
                        verifySignature("a1soln.Restaurant", "protected",
                                false, String.class, "priceRange", ""),
                        verifySignature("a1soln.Restaurant", "protected",
                                false, List.class, "cuisines", ""),
                        verifySignature("a1soln.Restaurant", "protected",
                                false, Map.class, "reviews", ""),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        null, null, String.class, String.class,
                                        String.class, List.class),
                        verifySignature("a1soln.Restaurant", "public", false, 
                                        void.class, "addReview", "a1soln.Review"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        String.class, "getName"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        String.class, "getName"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        String.class, "getNeighbourhood"),
                        verifySignature("a1soln.Restaurant", "public", false, 
                                        String.class, "getPriceRange"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        List.class, "getCuisines"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        Map.class, "getReviews"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        "a1soln.Review", "getReview", int.class),
                        verifySignature("a1soln.Restaurant", "public", false, 
                                        double.class, "percentageThumbsUp"),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        void.class, "addCuisine", String.class),
                        verifySignature("a1soln.Restaurant", "public", false,
                                        void.class, "removeCuisine", String.class),
                        verifySignature("a1soln.LicensedRestaurant", "private",
                                        false, Date.class, "expiryDate", ""),
                        verifySignature("a1soln.LicensedRestaurant", "public",
                                        false, null, null, String.class,
                                        String.class, String.class, List.class,
                                        Date.class),
                        verifySignature("a1soln.LicensedRestaurant", "public",
                                        false, Date.class, "getExpiryDate"),
                        verifySignature("a1soln.LicensedRestaurant", "public", 
                                        false, void.class, "setExpiryDate", 
                                        Date.class),
                        verifySignature("a1soln.TakeoutRestaurant", "private",
                                        false, int.class, "avgWaitTime", ""),
                        verifySignature("a1soln.TakeoutRestaurant", "public",
                                        false, null, null, String.class, 
                                        String.class, String.class, List.class,
                                        int.class),
                        verifySignature("a1soln.TakeoutRestaurant", "public", 
                                        false, int.class, "getAvgWaitTime"),
                        verifySignature("a1soln.Takeout", "public", false, 
                                        int.class, "getAvgWaitTime")
                        )
             );
        System.out.println("All type checks passed!");
        System.out.println("Note that this does NOT mean your code works perfectly.");
    }

    
    /**
     * Returns the name of any signature that doesn't match and null
     * otherwise.
     * @param className
     * @param accessModifier
     * @param staticWanted
     * @param returnType Return type of method; null for constructors
     *  and fields.
     * @param methodName    Null for constructors.
     * @param argClasses
     * @return
     */
    private static String verifySignature(String className, String accessModifier,
            boolean staticWanted, Object returnType, String methodName,
            Object ... argClasses) {
        Class<?> c = null;
        
        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {}
        
        List<Class<?>> argTypes = argClassesToList(argClasses);
        Class<?>[] args = argTypes == null ? null : argTypes.toArray(new Class<?>[0]);
        
        try {
            int modifiers = getModifiers(returnType, c, methodName, args);
            
            // Check access modifiers
            checkModifiers(accessModifier, staticWanted, modifiers);
        } catch (NoSuchMethodException e) {
            if (VERBOSE) {
                e.printStackTrace();
            }
            return className + "." + methodName;
        } catch (SecurityException e) {
            return className + "." + methodName;
        } catch (NoSuchFieldException e) {
            return className + "." + methodName;
        } catch (ClassNotFoundException e) {
            // Problem with type checker inputs
            e.printStackTrace();
        }
        
        return null;
    }

    
    /**
     * Raise an exception if the expected access modifier does not match
     * the declared modifier.
     * @param accessModifier    One of the Java access modifiers.
     * @param staticWanted        True iff a static method/field is sought.
     * @param modifiers            Modifiers as found by reflection.
     * @throws NoSuchMethodException
     */
    private static void checkModifiers(String accessModifier,
            boolean staticWanted, int modifiers)
            throws NoSuchMethodException {
        
        // Check staticness
        if (Modifier.isStatic(modifiers) != staticWanted) {
            throw new NoSuchMethodException();
        }
        
        // Check access modifiers
        if (accessModifier.equals("public") &&
                !Modifier.isPublic(modifiers)) {
            throw new NoSuchMethodException();
        } else if (accessModifier.equals("protected") &&
                !Modifier.isProtected(modifiers)) {
            throw new NoSuchMethodException();
        } else if (accessModifier.equals("default") &&
                !Modifier.isPublic(modifiers) &&
                !Modifier.isProtected(modifiers) &&
                !Modifier.isPrivate(modifiers)) {
            throw new NoSuchMethodException();
        } else if (accessModifier.equals("private") &&
                !Modifier.isPrivate(modifiers)) {
            throw new NoSuchMethodException();
        }
    }

    
    /**
     * Get the modifiers for the specified constructor/method/field with
     * the matching signature.
     * @param returnType Return type of the method signature.
     * @param checkedClass The class containing the method.
     * @param name The method/field name to be checked (null for constructors) 
     * @param argTypes Types of arguments for signature.
     * @return
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    private static int getModifiers(Object returnType, Class<?> checkedClass,
            String name, Class<?>[] args)
                    throws NoSuchMethodException, NoSuchFieldException,
                    ClassNotFoundException {
        int modifiers;
        if (name == null) {
            // Check constructors 
            Constructor<?> con = checkedClass.getDeclaredConstructor(args);
            modifiers = con.getModifiers();
        } else if (args == null) {
            // Check fields
            Field fld = checkedClass.getDeclaredField(name);
            modifiers = fld.getModifiers();
            if (fld.getType() != toClassName(returnType)) {
                throw new NoSuchMethodException();
            }
        } else {
            // Check methods
            Method meth = checkedClass.getDeclaredMethod(name, args);
            modifiers = meth.getModifiers();
            if (meth.getReturnType() != toClassName(returnType)) {
                throw new NoSuchMethodException();
            }
        }
        return modifiers;
    }

    
    /**
     * Take a list of arguments with classes or class names, turn
     * them into a list, and return that list.
     * @param argClasses
     * @return
     */
    private static List<Class<?>> argClassesToList(Object... argClasses) {
        // Add each argument type to a list
        List<Class<?>> argTypes = new ArrayList<Class<?>>();
        
        if (argClasses.length > 0 && argClasses[0].equals("")) {
            argTypes = null;
        } else {
            for (Object argClass : argClasses) {
                try {
                    if (argClass.getClass() == String.class) {
                        argTypes.add(toClassName((String) argClass));
                    } else {
                        argTypes.add((Class<?>) argClass);
                    }
                } catch (ClassNotFoundException e) {
                    // Error with type checker input
                    e.printStackTrace();
                }
            }
        }
        return argTypes;
    }

    /**
     * Resolve a class from argClassRaw if a String is provided or
     * else reutrn the original argument.
     * @param argClassRaw
     * @return
     * @throws ClassNotFoundException
     */
    protected static Class<?> toClassName(Object argClassRaw)
            throws ClassNotFoundException {
        Class<?> cls;
        if (argClassRaw.getClass() != String.class) {
            return (Class<?>) argClassRaw;
        }
        
        String argClass = (String) argClassRaw;
        
        if (argClass.equals("int")) {
            cls = int.class;
        } else if (argClass.equals("void")) {
            cls = void.class;
        } else if (argClass.equals("boolean")) {
            cls = boolean.class;
        } else if (argClass.equals("String")) {
            cls = java.lang.String.class;    // Convenience
        } else {
            cls = Class.forName(argClass);
        }
        return cls;
    }

    
    /**
     * Return a list of Strings from methodNames with nulls filtered out.
     * @param methodNames
     * @return
     */
    private static List<String> verifySignatures(String ... methodNames) {
        List<String> missing = new ArrayList<String>();
        for (String methodName : methodNames) {
            if (methodName != null) {
                missing.add(methodName);
            }
        }
        return missing;
    }

    /**
     * Print missing items. Exit if anything is missing.
     * @param string Message for missing items. Use "%s" for missing item name.
     * @param missingItems Items that were missing
     * @return
     */
    private static void printMissing(String string, List<String> missingItems) {
        if (missingItems.size() > 0) {
            for (String itemName : missingItems) {
                itemName = itemName.replaceAll("\\.null$", " constructor");
                System.out.println(string.replaceAll("%s", itemName));
            }
            System.exit(1);
        }
    }

    /**
     * Generate a list of classes/interfaces that are missing.
     * @param classNames
     * @param wantInterface Set to true iff looking for an interface.
     * @param wantAbstract Set to true iff looking for abstract class.
     * @return List of classes that are missing.
     */
    private static List<String> verifyClasses(boolean wantInterface,
            boolean wantAbstract,
            String ... classNames) {
        List<String> missing = new ArrayList<String>();
        for (String className : classNames) {
            try {
                Class<?> c = Class.forName(className);
                if (c.isInterface() != wantInterface) {
                    throw new ClassNotFoundException();
                }

                // Interfaces are also abstract, so need both
                if (Modifier.isAbstract(c.getModifiers()) !=
                        (wantAbstract | wantInterface)) {
                    throw new ClassNotFoundException();
                }
            } catch (ClassNotFoundException e) {
                missing.add(className);
            } catch (NoClassDefFoundError e) {
                missing.add(className);
            }
        }
        return missing;
    }    
}
