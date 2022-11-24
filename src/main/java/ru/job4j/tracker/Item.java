package ru.job4j.tracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Comparable<Item> {
    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();

    public Item(int id) {
        this.id = id;
    }

    public Item(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Item another) {
        return Integer.compare(id, another.id);
    }
}