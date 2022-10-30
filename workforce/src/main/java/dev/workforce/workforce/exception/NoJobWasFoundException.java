package dev.workforce.workforce.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class NoJobWasFoundException extends AbstractThrowableProblem {
    public NoJobWasFoundException() {
        super(URI.create("positions/not-found"),
                "no position was found",
                Status.NOT_FOUND,
                String.format("no position fits for the search"));
    }
}
