package com.thebook.thebookapi.resolver;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.thebook.thebookapi.data.entity.ScriptureEntity;
import com.thebook.thebookapi.repository.ScriptureRepository;
import com.thebook.thebookapi.types.Scripture;
import com.thebook.thebookapi.types.ScriptureIdInput;
import com.thebook.thebookapi.types.ScripturePayload;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ScriptureQueryResolver {
    @Autowired
    ScriptureRepository scriptureService;

    @DgsQuery
    public ScripturePayload scriptureByIds(@InputArgument ScriptureIdInput input) {
        List<? extends ScriptureEntity> scripture;

        // todo validate length, overflow length into chapter, book
        // todo convert start + length into endId??
        if (input.getLength() != null) {
            scripture = scriptureService.getScriptureById(input.getStartId(), input.getLength());
        } else if (input.getEndId() != null) {
            scripture = scriptureService.getScriptureByIds(input.getStartId(), input.getEndId());
        } else {
            scripture = scriptureService.getScriptureById(input.getStartId());
        }

        return new ScripturePayload(
                scripture.size(),
                scripture.stream()
                        .map(ScriptureQueryResolver::mapEntity)
                        .collect(Collectors.toList())
        );
    }

    private static Scripture mapEntity(ScriptureEntity se) {
        return new Scripture(
                se.id,
                Integer.parseInt(se.b),
                Integer.parseInt(se.c),
                Integer.parseInt(se.v),
                se.t
        );
    }
}
