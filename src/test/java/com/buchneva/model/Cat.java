package com.buchneva.model;

import java.util.List;

public class Cat {

        public String name;
        public int age;
        public List<String> ability;
        public boolean likesToSleep;
        public Parameters parameters;

        public static class Parameters {
            public double weight;
            public int height;
            public String coloration;
        }


}
