package tests.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PetModels {
    int id;
    Category category;
    String name;
    List<String> photoUrls;
    Tag tag;
    PetStatus status;
}

