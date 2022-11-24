package ru.job4j.tracker;

public class PermissionUse {
    public static void main(String[] args) {
        var permission = Permission.of()
                .id(1)
                .name("name")
                .rules("rules")
                .build();
        System.out.println(permission);
    }
}
