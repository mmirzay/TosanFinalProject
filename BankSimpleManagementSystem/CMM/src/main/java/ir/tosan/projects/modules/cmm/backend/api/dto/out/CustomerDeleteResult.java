package ir.tosan.projects.modules.cmm.backend.api.dto.out;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerDeleteResult {
    private final Long code;
    private final String fullName;
}
