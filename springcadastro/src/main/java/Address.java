import javax.persistence.*;


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
