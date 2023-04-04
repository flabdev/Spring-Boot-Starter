package com.fissionlab.coe.controller;

import com.fissionlab.coe.model.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.links.LinkParameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/people")
@Tag(name = "People", description = "Endpoints for managing people")
public class PersonController {

    private List<Person> personList = createList();


    @GetMapping
    @Operation(
        summary = "Finds all people",
        description = "Finds all people.",
        tags = { "People" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = Person.class))
                    )
                }
            ),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Iterable<Person>> getAllPersons() {
        return ResponseEntity.ok(personList);
    }

    // Creates the example response without any annotations
    // Still figures it out even without defining the content property of @ApiResponse
    // But without the annotation I can't set the media type?
    @GetMapping("/{id}")
    @Operation(
        summary = "Finds a person",
        description = "Finds a person by their Id.",
        tags = { "People" },
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            ),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            // Need the empty content otherwise it fills it with the example person schema
            // Setting empty content also hides the box in the swagger ui
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )

    public ResponseEntity<Person> getPersonById(@PathVariable("id") @Parameter(description = "The Id of the person to find.") UUID id) {

        Person person = getPerson(id);

        if (person != null) {
            return  ResponseEntity.ok(person);
        } else {
            throw new NoSuchElementException("Person with id: " + id + " does not exist");
        }
    }

    private Person getPerson(UUID id) {
        Person person = null;
        for (Person person1 : personList) {
            if (person1.getId().equals(id)) {
                person = person1;
                break;
            }
        }
        return person;
    }

    @PostMapping
    @Operation(
        summary = "Adds a new person",
        description = "Adds a new person by passing in a JSON representation of the person.",
        tags = { "People" },
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                links = @Link(name = "get", operationId = "get", parameters = @LinkParameter(name = "id", expression = "$request.body.id")),
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            ),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person saved = new Person(
                UUID.randomUUID(),
                person.getFirstName(),
                person.getLastName()
        );

       personList.add(saved);
        URI uri =
            MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Updates a person's information",
        description = "Updates a person's information by passing in their Id and a JSON representation of the updated person.",
        tags = { "People" },
        responses = {
            @ApiResponse(
                description = "Updated",
                responseCode = "200",
                links = @Link(name = "get", operationId = "get", parameters = @LinkParameter(name = "id", expression = "$request.body.id")),
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))
            ),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Person> updatePerson(
        @PathVariable("id") @Parameter(description = "The Id of the person to update.") UUID id,
        @RequestBody Person person
    ) {
        Person existingPerson = getPerson(id);

        if (existingPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            Person saved = new Person(
                    id,
                    person.getFirstName(),
                    person.getLastName()
            );
            return ResponseEntity.ok(saved);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletes a person",
        description = "Deletes a person by their Id.",
        tags = { "People" },
        responses = {
            @ApiResponse(description = "Deleted", responseCode = "204", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(description = "The Id of the person to delete.") UUID id) {
        Person existingPerson = getPerson(id);

        if (existingPerson == null) {
            return ResponseEntity.notFound().build();
        } else {
            personList.remove(existingPerson);
            return ResponseEntity.noContent().build();
        }
    }

    private static List<Person> createList() {
        List<Person> tempPersons = new ArrayList<>();
        Person person1 = new Person(UUID.randomUUID(), "John", "Deo");
        tempPersons.add(person1);
        return tempPersons;
    }
}
