package com.cubivue.base.models;

/**
 * Model class containing personal information that will be saved to SharedPreferences.
 */
public class SharedPreferenceEntry {

    private final String name;
    private final int age;
    private final float weight;
    private final long dob;
    private final boolean isMarried;

    public SharedPreferenceEntry(String name, int age, float weight, long dob, boolean isMarried) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.dob = dob;
        this.isMarried = isMarried;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public long getDob() {
        return dob;
    }

    public boolean isMarried() {
        return isMarried;
    }

}
