package dev.workforce.workforce.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class EmailAddressAlreadyTakenException extends AbstractThrowableProblem {
    public EmailAddressAlreadyTakenException(String email) {
        super(URI.create("client/email-is-taken"),
                "email address is taken",
                Status.NOT_ACCEPTABLE,
                String.format("The given email address is registered in the system %s", email));
    }
}
