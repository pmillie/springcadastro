# springcadastro
API simples para gerenciar pessoas 
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    // Construtores, getters e setters
}

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String cep;

    private String number;

    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    private boolean isMain;

    // Construtores, getters e setters
}

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return ResponseEntity.created(URI.create("/api/people/" + savedPerson.getId())).body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            person.setName(updatedPerson.getName());
            person.setDateOfBirth(updatedPerson.getDateOfBirth());
            person.setAddresses(updatedPerson.getAddresses());
            personRepository.save(person);
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        Optional<Person> optional