package com.ding.office.utils;

import java.io.*;

public class CloneUtil {
    public static <T extends Serializable> T clone(T obj) {
        T cloneObj=null;
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream obs = new ObjectOutputStream(out);
            obs.writeObject(obj);
            obs.close();

            ByteArrayInputStream ios = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(ios);
            cloneObj=(T)ois.readObject();
            ois.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return cloneObj;
    }
}
