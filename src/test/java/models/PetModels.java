package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PetModels {
    private int id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private PetTag petTag;
    private PetStatus status;
}

