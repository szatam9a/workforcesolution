package dev.workforce.workforce.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class JobNotFoundException extends AbstractThrowableProblem {
    public JobNotFoundException(Long id) {
        super(URI.create("position/not-found"),
                "Position not found With given Id ",
                Status.NOT_FOUND,
                String.format("The given apikey is invalid %d", id));
    }
}
