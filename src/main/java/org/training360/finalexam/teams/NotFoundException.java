package org.training360.finalexam.teams;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;


public class NotFoundException extends AbstractThrowableProblem {

    public NotFoundException( long id) {
        super(URI.create("teams/not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Not found with id '%d'", id));
    }
}