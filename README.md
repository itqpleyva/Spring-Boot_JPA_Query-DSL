# Spring-Boot_JPA_Query-DSL

<h3>JPA QUERY DSL EXAMPLE</h3>

DEPENDENCIES:

          <dependencies>
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
            </dependency>
            <dependency>
              <groupId>io.kwy.boot</groupId>
              <artifactId>spring-boot-starter-data-jpa</artifactId>
              <version>1.5.3.1.RELEASE</version>
            </dependency>
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-devtools</artifactId>
              <scope>runtime</scope>
              <optional>true</optional>
            </dependency>
            <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-test</artifactId>
              <scope>test</scope>
              <exclusions>
                <exclusion>
                  <groupId>org.junit.vintage</groupId>
                  <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
              </exclusions>
            </dependency>
            <dependency>
                  <groupId>com.querydsl</groupId>
                  <artifactId>querydsl-apt</artifactId>
                  <version>${querydsl.version}</version>
                  <scope>provided</scope>
              </dependency>

              <dependency>
                  <groupId>com.querydsl</groupId>
                  <artifactId>querydsl-jpa</artifactId>
                  <version>${querydsl.version}</version>
              </dependency>
            <dependency>
              <groupId>org.postgresql</groupId>
              <artifactId>postgresql</artifactId>
              <scope>runtime</scope>
            </dependency>
          </dependencies>
          
         <plugin>
          <groupId>com.mysema.maven</groupId>
          <artifactId>apt-maven-plugin</artifactId>
          <version>1.1.3</version>
          <executions>
            <execution>
              <goals>
                <goal>process</goal>
              </goals>
              <configuration>
                <outputDirectory>target/generated-sources/java</outputDirectory>
                <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
              </configuration>
            </execution>
          </executions>
        </plugin>
        
Automatic generated dsl model from user Model:
  
        @Generated("com.querydsl.codegen.EntitySerializer")
      public class QUserModel extends EntityPathBase<UserModel> {

          private static final long serialVersionUID = -2080028327L;

          public static final QUserModel userModel = new QUserModel("userModel");

          public final StringPath gender = createString("gender");

          public final NumberPath<Long> id = createNumber("id", Long.class);

          public final StringPath name = createString("name");

          public QUserModel(String variable) {
              super(UserModel.class, forVariable(variable));
          }

          public QUserModel(Path<? extends UserModel> path) {
              super(path.getType(), path.getMetadata());
          }

          public QUserModel(PathMetadata metadata) {
              super(UserModel.class, metadata);
          }

      }
      
UserModel Repository:
      
                @Repository
          public interface UserRepository extends JpaRepository<UserModel, Long>, QuerydslPredicateExecutor<UserModel> {

          }
          
  Using DSL query:
  
          @SpringBootApplication
        public class QueryDslApplication implements CommandLineRunner {

          @Autowired
          private UserRepository userRepository;

          @Autowired
          private EntityManager entityManager;

          public static void main(String[] args) {
            SpringApplication.run(QueryDslApplication.class, args);
            System.out.println("Query DSL example");
          }

          @Override
          public void run(String... args) throws Exception {

            UserModel user1 = new UserModel("Pepe", "M");
            UserModel user2 = new UserModel("Pepe1", "F");
            UserModel user3 = new UserModel("Pepe2", "M");
            UserModel user4 = new UserModel("Pepe3", "F");
            List<UserModel> users = Arrays.asList(user1, user1, user2, user3, user4);

            userRepository.saveAll(users);

            QUserModel quser = QUserModel.userModel;
            JPQLQuery<?> query = new JPAQuery<>(entityManager);

            List<UserModel> list_user_finded = query.select(quser).from(quser).where(quser.name.like("%P%")).fetch();

            System.out.println(list_user_finded);
          }

        }

In console you will see as result of the DSL query all users with name (quser.name.like("%P%") contains "P"):

      [{ id='1', name='Pepe', gender='M'}, { id='2', name='Pepe1', gender='F'}, { id='3', name='Pepe2', gender='M'}, { id='4', name='Pepe3', gender='F'}]
      

