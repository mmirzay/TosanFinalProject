package ir.tosan.projects.modules.cmm.backend.api.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerGetResult {
    private String fullName;
    private Long nationalId;
}
