package ru.knastnt.meshtest.web.profile;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.knastnt.meshtest.model.Profile;
import ru.knastnt.meshtest.repository.CrudProfileRepository;
import ru.knastnt.meshtest.util.exception.ErrorInfo;

import javax.validation.Valid;
import java.net.BindException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {
    static final String REST_URL = "/profiles";

    private final CrudProfileRepository crudProfileRepository;

    public ProfileRestController(CrudProfileRepository crudProfileRepository) {
        this.crudProfileRepository = crudProfileRepository;
    }

    @PostMapping(value = "/set", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity set(@RequestBody @Valid Profile profile) throws DataIntegrityViolationException {
//        User created = super.create(userTo);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL).build().toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
        /*
        в случае успеха возвращает id записи пользователя

        status 200
        {
            “idUser”: int
        }

        В случае некорректного email
        status 400

        {
            “msg”: string
        }

        В случае если email уже передавался (реализовать через фильтр)
        status 403

        {
            “msg”: string
        }

         */
//        if (result.hasErrors()) {
//            String errorFieldsMsg = result.getFieldErrors().stream()
//                    .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
//                    .collect(Collectors.joining("<br>"));
//
//            return ResponseEntity.badRequest().body(new ErrorInfo(errorFieldsMsg));
////            throw new BindException(errorFieldsMsg);
//        }




//        return ResponseEntity.ok(savedProfile.getId().toString());
        try {
            profile = crudProfileRepository.save(profile);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("такой емаил уже есть");
        }

        Map<String, Long> result = new HashMap<>();
        result.put("userId", profile.getId());
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/last", produces = MediaType.APPLICATION_JSON_VALUE)
    public Profile getLast() {
//        return super.get(authUserId());
        return null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profile> getAll() {
//        return super.getAll();
        return crudProfileRepository.findAll();
    }

    @GetMapping("/{id}")
    public Profile get(@PathVariable long id) {
//        return super.get(id);

        /*

        status 404
        в случае если запись не найдена
        {
            “msg”: string
        }

         */
        return crudProfileRepository.getOne(id);
    }

    @PostMapping("/get")
    public Profile getByEmail(String email){
        return crudProfileRepository.getByEmailIgnoreCase(email);
    }
}
