package ru.knastnt.meshtest.web.profile;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
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

import static ru.knastnt.meshtest.util.ExceptionUtil.checkNotFound;

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
    public ResponseEntity<Map<String, Long>> set(@RequestBody @Valid Profile profile) throws DataIntegrityViolationException {

        if (profile.getId() != null) throw new DataIntegrityViolationException("Id создаваемого профиля не должен быть указан");

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
        return checkNotFound(crudProfileRepository.getTopByOrderByIdDesc(), null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profile> getAll() {
        return crudProfileRepository.findAll();
    }

    @GetMapping("/{id}")
    public Profile get(@PathVariable long id) {
//        return checkNotFound(crudProfileRepository.getOne(id), "id = " + id); извлекает прокси, а потом проблемы с сериализацией
        return checkNotFound(crudProfileRepository.findById(id).orElse(null), "id = " + id);
    }

    @PostMapping("/get")
    public Profile getByParam(@RequestBody Map<String, String> param){
        if (param.size() != 1 || !param.containsKey("email")) throw new RuntimeException("переданы неверные параметры");
        String email = param.get("email");
        return checkNotFound(crudProfileRepository.getByEmailIgnoreCase(email), "email = " + email);
    }
}
