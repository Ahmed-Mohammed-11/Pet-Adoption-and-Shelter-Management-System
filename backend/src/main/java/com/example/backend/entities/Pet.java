package com.example.backend.entities;

public record Pet(int id,
                  String name,
                  String species,
                  int age,
                  boolean gender,
                  String description,
                  String breed,
                  int house_training,
                  String behavior) {

}
