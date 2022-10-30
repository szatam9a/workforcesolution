package dev.workforce.workforce.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class InvalidApiKeyException extends AbstractThrowableProblem {
    public InvalidApiKeyException(String apikey) {
        super(URI.create("client/invalid-apikey"),
                "Invalid client apikey ",
                Status.NOT_ACCEPTABLE,
                String.format("The given apikey is invalid %s", apikey));
    }
}
