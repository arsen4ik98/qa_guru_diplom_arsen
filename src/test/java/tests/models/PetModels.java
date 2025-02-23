package tests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder

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


}
