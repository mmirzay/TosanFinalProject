package ir.tosan.projects.modules.cmm.backend.api.dto.out;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerUpdateResult {
    private final Long code;
    private final String message;
}
