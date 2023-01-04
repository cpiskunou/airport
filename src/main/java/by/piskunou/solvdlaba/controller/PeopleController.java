package by.piskunou.solvdlaba.controller;

import by.piskunou.solvdlaba.dto.PersonDTO;
import by.piskunou.solvdlaba.services.PeopleService;
import by.piskunou.solvdlaba.util.PeopleDTOValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDTOValidator peopleDTOValidator;
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleDTOValidator peopleDTOValidator, PeopleService peopleService) {
        this.peopleDTOValidator = peopleDTOValidator;
        this.peopleService = peopleService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrate(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        peopleDTOValidator.validate(personDTO, bindingResult);
        if(bindingResult.hasErrors()) {

        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
