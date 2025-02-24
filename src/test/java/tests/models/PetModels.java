package tests.models;

import java.util.List;

public class PetModels {
    public int id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public PetTag PetTag;
    public PetStatus status;


    public PetModels(int id, Category category, String name, List<String> photoUrls, PetTag PetTag, PetStatus status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.PetTag = PetTag;
        this.status = status;
    }
//    public PetModels(int id, Category category, String name, List<String> photoUrls, Tag tag, PetStatus status) {
//        this.id = id;
//        this.category = category;
//        this.name = name;
//        this.photoUrls = photoUrls;
//        this.tag = tag;
//        this.status = status;
//    }
    }
