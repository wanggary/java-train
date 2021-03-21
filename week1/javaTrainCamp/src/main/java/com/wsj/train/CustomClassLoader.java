package com.wsj.train;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CustomClassLoader extends ClassLoader {
    public static void main(String[] args) {
        try {
            Object hello = new CustomClassLoader().findClass("Hello").newInstance();
            hello.getClass().getMethod("hello").invoke(hello.getClass().newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] helloBytes = getHelloBytes("src\\main\\resources\\Hello.xlass");

        return defineClass(name, helloBytes, 0, helloBytes.length);
    }

    public byte[] getHelloBytes(String file) {
        FileInputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {
            in = new FileInputStream(file);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            bytes = out.toByteArray();

            for (int i = 0; i < bytes.length; i++) {
                bytes[i]  = (byte) (255 - bytes[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
}
