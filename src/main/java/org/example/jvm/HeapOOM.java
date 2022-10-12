package org.example.jvm;

import java.util.ArrayList;

public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> oomObjects = new ArrayList<>();

        while (true) {
            oomObjects.add(new OOMObject());
        }
    }
}
