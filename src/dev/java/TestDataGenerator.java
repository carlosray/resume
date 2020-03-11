import com.resume.model.LanguageLevel;
import com.resume.model.LanguageType;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.sql.*;
import java.util.*;

public class TestDataGenerator {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String JDBC_URL = "jdbc:sqlite:resumedb.s3db";

    private static final String PHOTO_PATH = "external/test-data/photos/";
    private static final String CERT_PATH = "external/test-data/certificates/";
    private static final String MEDIA_DIR = "E:/Projects/resume/src/main/webapp/media";
    private static final String COUNTRY = "Russia";
    private static final String[] CITIES = {"Krasnodar", "Moscow", "Saint-Petersburg"};
    private static final String[] FOREIGN_LANGUAGES = {"Spanish", "French", "Italian", "German"};
    private static final String PASSWORD_HASH = "$2a$10$q7732w6Rj3kZGhfDYSIXI.wFp.uwTSi2inB2rYHvm1iDIAf1J1eVq";

    private static final String[] HOBBIES = {"Cycling", "Handball", "Football", "Basketball", "Bowling", "Boxing", "Volleyball", "Baseball", "Skating", "Skiing", "Table tennis", "Tennis",
            "Weightlifting", "Automobiles", "Book reading", "Cricket", "Photo", "Shopping", "Cooking", "Codding", "Animals", "Traveling", "Movie", "Painting", "Darts", "Fishing", "Kayak slalom",
            "Games of chance", "Ice hockey", "Roller skating", "Swimming", "Diving", "Golf", "Shooting", "Rowing", "Camping", "Archery", "Pubs", "Music", "Computer games", "Authorship", "Singing",
            "Foreign lang", "Billiards", "Skateboarding", "Collecting", "Badminton", "Disco"};

    private static List<LanguageType> languageTypes = new ArrayList<>(EnumSet.allOf(LanguageType.class));
    private static List<LanguageLevel> languageLevels = new ArrayList<>(EnumSet.allOf(LanguageLevel.class));

    static private final String CONTENT_TEXT = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.";
    static private final List<String> SENTENCES;

    static {
        try {
            Class.forName(DRIVER);
            System.out.println(("Драйвер (JDBC) загружен: " + DRIVER));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String[] sentences = CONTENT_TEXT.split("\\.");
        List<String> sentencesList = new ArrayList<>();
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.length() > 0) {
                sentencesList.add(sentence);
            }
        }
        SENTENCES = sentencesList;
    }

    private static final Random random = new Random();
    private static Date birthDay = null;

    public static void main(String[] args) throws IOException {
        clearMedia();
        List<Certificate> certificates = loadCertificates();
        List<Profile> profiles = loadProfiles();
        List<ProfileConfig> profileConfigs = getProfileConfigs();
        try (Connection connection = DriverManager.getConnection(JDBC_URL)) {
            clearDb(connection);
            connection.setAutoCommit(false);
            for (Profile profile : profiles) {
                ProfileConfig profileConfig = profileConfigs.get(random.nextInt(profileConfigs.size()));
                createProfile(connection, profile, profileConfig, certificates);
                System.out.println("Created profile for " + profile.firstName + " " + profile.lastName);
            }
            insertSkillCategories(connection);
            connection.commit();
            System.out.println("Data generated successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertSkillCategories(Connection connection) throws SQLException {
        Map<String, Set<String>> categories = createSkillMap();
        PreparedStatement ps = connection.prepareStatement("insert into skill_category (category) values (?)");
        for (String category : categories.keySet()) {
            ps.setString(1, category);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    private static void clearDb(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate("delete from profile");
        st.executeUpdate("delete from certificate");
        st.executeUpdate("delete from course");
        st.executeUpdate("delete from education");
        st.executeUpdate("delete from hobby");
        st.executeUpdate("delete from language");
        st.executeUpdate("delete from practic");
        st.executeUpdate("delete from skill");
        st.executeUpdate("delete from skill_category");
        System.out.println("Db cleared");
    }


    private static void clearMedia() throws IOException {
        if (Files.exists(Paths.get(MEDIA_DIR))) {
            Files.walkFileTree(Paths.get(MEDIA_DIR), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        System.out.println("Media dir cleared");
    }

    private static List<Certificate> loadCertificates() {
        File[] files = new File(CERT_PATH).listFiles();
        List<Certificate> list = new ArrayList<>(files.length);
        for (File f : files) {
            String name = f.getName().replace("-", " ");
            name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            list.add(new Certificate(name, f.getAbsolutePath()));
        }
        return list;
    }

    private static List<Profile> loadProfiles() {
        File[] photos = new File(PHOTO_PATH).listFiles();
        List<Profile> list = new ArrayList<>(photos.length);
        for (File file : photos) {
            String[] names = file.getName().replace(".jpg", "").split(" ");
            list.add(new Profile(names[0], names[1], file.getAbsolutePath()));
        }
        Collections.sort(list, (o1, o2) -> {
            int firstNameCompare = o1.firstName.compareTo(o2.firstName);
            if (firstNameCompare != 0) {
                return firstNameCompare;
            } else {
                return o1.lastName.compareTo(o2.lastName);
            }
        });
        return list;
    }

    private static List<ProfileConfig> getProfileConfigs() {
        List<ProfileConfig> res = new ArrayList<>();
        res.add(new ProfileConfig("Junior java trainee position", "Java core course with developing one simple console application", new Course[]{Course.createCoreCourse()}, 0));
        res.add(new ProfileConfig("Junior java trainee position", "One Java professional course with developing web application blog (Link to demo is provided)",
                new Course[]{Course.createBaseCourse()}, 0));
        res.add(new ProfileConfig("Junior java developer position", "One Java professional course with developing web application resume (Link to demo is provided)",
                new Course[]{Course.createAdvancedCourse()}, 0));
        res.add(new ProfileConfig("Junior java developer position", "One Java professional course with developing web application resume (Link to demo is provided)",
                new Course[]{Course.createAdvancedCourse()}, 1));
        res.add(new ProfileConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
                new Course[]{Course.createAdvancedCourse(), Course.createBaseCourse()}, 1));
        res.add(new ProfileConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
                new Course[]{Course.createAdvancedCourse(), Course.createBaseCourse()}, 1));
        res.add(new ProfileConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
                new Course[]{Course.createAdvancedCourse(), Course.createBaseCourse()}, 1));
        res.add(new ProfileConfig("Junior java developer position", "Two Java professional courses with developing two web applications: blog and resume (Links to demo are provided)",
                new Course[]{Course.createAdvancedCourse(), Course.createBaseCourse()}, 2));
        res.add(new ProfileConfig("Junior java developer position",
                "Three Java professional courses with developing one console application and two web applications: blog and resume (Links to demo are provided)",
                new Course[]{Course.createAdvancedCourse(), Course.createBaseCourse(), Course.createCoreCourse()}, 2));
        return res;
    }

    private static Map<String, Set<String>> createSkillMap() {
        Map<String, Set<String>> skills = new LinkedHashMap<>();
        skills.put("Languages", new LinkedHashSet<>());
        skills.put("DBMS", new LinkedHashSet<>());
        skills.put("Web", new LinkedHashSet<>());
        skills.put("Java", new LinkedHashSet<>());
        skills.put("IDE", new LinkedHashSet<>());
        skills.put("CVS", new LinkedHashSet<>());
        skills.put("Web Servers", new LinkedHashSet<>());
        skills.put("Build system", new LinkedHashSet<>());
        skills.put("Cloud", new LinkedHashSet<>());
        return skills;
    }

    private static final class ProfileConfig {
        private final String objective;
        private final String summary;
        private final Course[] courses;
        private final int certificates;

        private ProfileConfig(String objective, String summary, Course[] courses, int certificates) {
            super();
            this.objective = objective;
            this.summary = summary;
            this.courses = courses;
            this.certificates = certificates;
        }
    }

    private static final class Profile {
        private long id;
        private final String firstName;
        private final String lastName;
        private final String photo;

        private Profile(String firstName, String lastName, String photo) {
            super();
            this.firstName = firstName;
            this.lastName = lastName;
            this.photo = photo;
        }

        @Override
        public String toString() {
            return String.format("Profile [firstName=%s, lastName=%s]", firstName, lastName);
        }
    }

    private static final class Certificate {
        private final String name;
        private final String largeImg;

        private Certificate(String name, String largeImg) {
            super();
            this.name = name;
            this.largeImg = largeImg;
        }
    }

    private static final class Course {
        private final String name;
        private final String company;
        private final String github;
        private final String responsibilities;
        private final String demo;
        private final Map<String, Set<String>> skills;

        private Course(String name, String company, String github, String responsibilities, String demo, Map<String, Set<String>> skills) {
            super();
            this.name = name;
            this.company = company;
            this.github = github;
            this.responsibilities = responsibilities;
            this.demo = demo;
            this.skills = skills;
        }

        static Course createCoreCourse() {
            Map<String, Set<String>> skills = createSkillMap();
            skills.get("Languages").add("Java");

            skills.get("DBMS").add("Mysql");

            skills.get("Java").add("Threads");
            skills.get("Java").add("IO");
            skills.get("Java").add("JAXB");
            skills.get("Java").add("GSON");

            skills.get("IDE").add("Eclipse for JEE Developer");

            skills.get("CVS").add("Git");
            skills.get("CVS").add("Github");

            skills.get("Build system").add("Maven");

            return new Course("Java Core Course", "Example Company", null, "Developing the java applications with JavaSE", null, skills);
        }

        static Course createBaseCourse() {
            Map<String, Set<String>> skills = createSkillMap();
            skills.get("Languages").add("Java");
            skills.get("Languages").add("SQL");

            skills.get("DBMS").add("Postgresql");

            skills.get("Web").add("HTML");
            skills.get("Web").add("CSS");
            skills.get("Web").add("JS");
            skills.get("Web").add("Foundation");
            skills.get("Web").add("JQuery");

            skills.get("Java").add("Servlets");
            skills.get("Java").add("Logback");
            skills.get("Java").add("JSP");
            skills.get("Java").add("JSTL");
            skills.get("Java").add("JDBC");
            skills.get("Java").add("Apache Commons");
            skills.get("Java").add("Google+ Social API");

            skills.get("IDE").add("Eclipse for JEE Developer");

            skills.get("CVS").add("Git");
            skills.get("CVS").add("Github");

            skills.get("Web Servers").add("Tomcat");

            skills.get("Build system").add("Maven");

            skills.get("Cloud").add("OpenShift");

            return new Course("Java Base Course", "Example Company", "https://github.com/example",
                    "Developing the web application 'blog' using free HTML template, downloaded from intenet. Populating database by test data and uploading web project to OpenShift free hosting",
                    "http://LINK_TO_DEMO_SITE", skills);
        }

        static Course createAdvancedCourse() {
            Map<String, Set<String>> skills = createSkillMap();
            skills.get("Languages").add("Java");
            skills.get("Languages").add("SQL");
            skills.get("Languages").add("PLSQL");

            skills.get("DBMS").add("Postgresql");

            skills.get("Web").add("HTML");
            skills.get("Web").add("CSS");
            skills.get("Web").add("JS");
            skills.get("Web").add("Bootstrap");
            skills.get("Web").add("JQuery");

            skills.get("Java").add("Spring MVC");
            skills.get("Java").add("Logback");
            skills.get("Java").add("JSP");
            skills.get("Java").add("JSTL");
            skills.get("Java").add("Spring Data JPA");
            skills.get("Java").add("Apache Commons");
            skills.get("Java").add("Spring Security");
            skills.get("Java").add("Hibernate JPA");
            skills.get("Java").add("Facebook Social API");

            skills.get("IDE").add("Eclipse for JEE Developer");

            skills.get("CVS").add("Git");
            skills.get("CVS").add("Github");

            skills.get("Web Servers").add("Tomcat");
            skills.get("Web Servers").add("Nginx");

            skills.get("Build system").add("Maven");

            skills.get("Cloud").add("AWS");

            return new Course("Java Advanced Course", "Example Company", "https://github.com/example",
                    "Developing the web application 'online-resume' using bootstrap HTML template, downloaded from intenet. Populating database by test data and uploading web project to AWS EC2 instance",
                    "http://LINK_TO_DEMO_SITE", skills);
        }
    }

    private static void createProfile(Connection connection, Profile profile, ProfileConfig profileConfig, List<Certificate> certificates) throws SQLException, IOException {
        insertProfileData(connection, profile, profileConfig);
        if (profileConfig.certificates > 0) {
            insertCertificates(connection, profileConfig.certificates, certificates, profile);
        }
        insertEducation(connection, profile);
        insertHobbies(connection, profile);
        insertLanguages(connection, profile);
        insertPractics(connection, profileConfig, profile);
        insertSkills(connection, profileConfig, profile);
        insertCourses(connection, profile);
    }

    private static void insertCourses(Connection connection, Profile profile) throws SQLException {
        if (random.nextBoolean()) {
            PreparedStatement ps = connection.prepareStatement("insert into course (id_profile, name, school, finish_date) values (?,?,?,?)");
            ps.setLong(1, profile.id);
            ps.setString(2, "Java Advanced Course");
            ps.setString(3, "SourceIt");
            Date finish = randomFinishEducation();
            if (finish.getTime() > System.currentTimeMillis()) {
                ps.setNull(4, Types.DATE);
            } else {
                ps.setDate(4, finish);
            }
            ps.executeUpdate();
            ps.close();
        }
    }

    private static void insertSkills(Connection connection, ProfileConfig profileConfig, Profile profile) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into skill (id_profile, category, value) values (?,?,?)");
        Map<String, Set<String>> map = createSkillMap();
        for (Course course : profileConfig.courses) {
            for (String key : map.keySet()) {
                map.get(key).addAll(course.skills.get(key));
            }
        }
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            if (!entry.getValue().isEmpty()) {
                ps.setLong(1, profile.id);
                ps.setString(2, entry.getKey());
                ps.setString(3, StringUtils.join(entry.getValue().toArray(), ","));
                ps.addBatch();
            }
        }
        ps.executeBatch();
        ps.close();
    }

    private static void insertPractics(Connection connection, ProfileConfig profileConfig, Profile profile) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into practic (id_profile, position, company, begin_date, finish_date, responsibilities, demo, src) values (?,?,?,?,?,?,?,?)");
        boolean currentCourse = random.nextBoolean();
        Date finish = addDateField(new Date(System.currentTimeMillis()), Calendar.MONTH, -(random.nextInt(3) + 1), false);
        for (Course course : profileConfig.courses) {
            ps.setLong(1, profile.id);
            ps.setString(2, course.name);
            ps.setString(3, course.company);
            if (currentCourse) {
                ps.setDate(4, addDateField(new Date(System.currentTimeMillis()), Calendar.MONTH, -1, false));
                ps.setNull(5, Types.DATE);
            } else {
                ps.setDate(4, addDateField(finish, Calendar.MONTH, -1, false));
                ps.setDate(5, finish);
                finish = addDateField(finish, Calendar.MONTH, -(random.nextInt(3) + 1), false);
            }
            ps.setString(6, course.responsibilities);
            if (course.demo == null) {
                ps.setNull(7, Types.VARCHAR);
            } else {
                ps.setString(7, course.demo);
            }
            if (course.github == null) {
                ps.setNull(8, Types.VARCHAR);
            } else {
                ps.setString(8, course.github);
            }
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    private static void insertLanguages(Connection connection, Profile profile) throws SQLException {
        List<String> languages = new ArrayList<>();
        languages.add("English");
        if (random.nextBoolean()) {
            int cnt = random.nextInt(1) + 1;
            List<String> otherLng = new ArrayList<>(Arrays.asList(FOREIGN_LANGUAGES));
            Collections.shuffle(otherLng);
            for (int i = 0; i < cnt; i++) {
                languages.add(otherLng.remove(0));
            }
        }
        PreparedStatement ps = connection.prepareStatement("insert into language (id_profile, name, level, type) values (?,?,?,?)");
        for (String language : languages) {
            LanguageType langType = languageTypes.get(random.nextInt(languageTypes.size()));
            LanguageLevel langLevel = languageLevels.get(random.nextInt(languageLevels.size()));
            ps.setLong(1, profile.id);
            ps.setString(2, language);
            ps.setString(3, langLevel.getDbValue());
            ps.setString(4, langType.getDbValue());
            ps.addBatch();
            if (langType != LanguageType.ALL) {
                ps.setLong(1, profile.id);
                ps.setString(2, language);
                LanguageLevel newLangLevel = languageLevels.get(random.nextInt(languageLevels.size()));
                while (newLangLevel == langLevel) {
                    newLangLevel = languageLevels.get(random.nextInt(languageLevels.size()));
                }
                ps.setString(3, langLevel.getDbValue());
                ps.setString(4, langType.getReverseType().getDbValue());
                ps.addBatch();
            }
        }
        ps.executeBatch();
        ps.close();
    }

    private static void insertHobbies(Connection connection, Profile profile) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into hobby (id_profile, name) values (?,?)");
        List<String> hobbies = new ArrayList<>(Arrays.asList(HOBBIES));
        Collections.shuffle(hobbies);
        for (int i = 0; i < 5; i++) {
            ps.setLong(1, profile.id);
            ps.setString(2, hobbies.remove(0));
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    private static void insertEducation(Connection connection, Profile profile) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into education (id_profile, summary, begin_year, finish_year, university, faculty) values (?,?,?,?,?,?)");
        ps.setLong(1, profile.id);
        ps.setString(2, "The specialist degree in Electronic Engineering");
        Date finish = randomFinishEducation();
        Date begin = addDateField(finish, Calendar.YEAR, -5, true);
        ps.setInt(3, begin.toLocalDate().getYear());
        if (finish.getTime() > System.currentTimeMillis()) {
            ps.setNull(4, Types.INTEGER);
        } else {
            ps.setInt(4, finish.toLocalDate().getYear());
        }
        ps.setString(5, "Kuban state University, Russia");
        ps.setString(6, "Computer Science");
        ps.executeUpdate();
        ps.close();
    }

    private static Date randomFinishEducation() {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(birthDay.getTime());
        cl.set(Calendar.DAY_OF_MONTH, 30);
        cl.set(Calendar.MONTH, Calendar.JUNE);
        int year = cl.get(Calendar.YEAR) + 21;
        cl.set(Calendar.YEAR, year + random.nextInt(3));
        return new Date(cl.getTimeInMillis());
    }

    private static Date addDateField(Date finish, int field, int value, boolean isBeginEducation) {
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(finish.getTime());
        cl.add(field, value);
        if (isBeginEducation) {
            cl.set(Calendar.DAY_OF_MONTH, 1);
            cl.set(Calendar.MONTH, Calendar.SEPTEMBER);
        }
        return new Date(cl.getTimeInMillis());
    }

    private static void insertCertificates(Connection connection, int certCount, List<Certificate> certificates, Profile profile) throws SQLException, IOException {
        Collections.shuffle(certificates);
        PreparedStatement ps = connection.prepareStatement("insert into certificate (id_profile, name, large_url, small_url) values (?,?,?,?)");
        for (int i = 0; i < certCount && i < certificates.size(); i++) {
            Certificate certificate = certificates.get(i);
            ps.setLong(1, profile.id);
            ps.setString(2, certificate.name);
            String uid = UUID.randomUUID().toString() + ".jpg";
            File photo = new File(MEDIA_DIR + "/certificates/" + uid);
            if (!photo.getParentFile().exists()) {
                photo.getParentFile().mkdirs();
            }
            String smallUid = uid.replace(".jpg", "-sm.jpg");
            Files.copy(Paths.get(certificate.largeImg), Paths.get(photo.getAbsolutePath()));
            ps.setString(3, "/media/certificates/" + uid);
            Thumbnails.of(photo).size(100, 100).toFile(Paths.get(photo.getAbsolutePath().replace(".jpg", "-sm.jpg")).toFile());
            ps.setString(4, "/media/certificates/" + smallUid);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();
    }

    private static String generatePhone() {
        StringBuilder phone = new StringBuilder("+7953");
        for (int i = 0; i < 7; i++) {
            int code = '1' + random.nextInt(9);
            phone.append(((char) code));
        }
        return phone.toString();
    }

    private static Date randomBirthDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, random.nextInt(30));
        calendar.set(Calendar.MONTH, random.nextInt(12));
        int year = calendar.get(Calendar.YEAR) - 30;
        calendar.set(Calendar.YEAR, year + random.nextInt(10));
        return new Date(calendar.getTimeInMillis());
    }

    private static void insertProfileData(Connection connection, Profile profile, ProfileConfig profileConfig) throws SQLException, IOException {
        PreparedStatement ps = connection.prepareStatement(
                "insert into profile (uid, first_name, last_name, birth_day, phone, email, country, city, objective, summary, large_photo, small_photo, info, password, completed, created, skype, vkontakte, facebook, linkedin, github, stackoverflow) " +
                        "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,true,?,?,?,?,?,?,?)");
        String lowerCaseProfileName = (profile.firstName + "-" + profile.lastName).toLowerCase();
        ps.setString(1, lowerCaseProfileName);
        ps.setString(2, profile.firstName);
        ps.setString(3, profile.lastName);
        birthDay = randomBirthDate();
        ps.setDate(4, birthDay);
        ps.setString(5, generatePhone());
        ps.setString(6, lowerCaseProfileName + "@gmail.com");
        ps.setString(7, COUNTRY);
        ps.setString(8, CITIES[random.nextInt(CITIES.length)]);
        ps.setString(9, profileConfig.objective);
        ps.setString(10, profileConfig.summary);

        String largeUid = UUID.randomUUID().toString() + ".jpg";
        File photo = new File(MEDIA_DIR + "/avatar/" + largeUid);
        if (!photo.getParentFile().exists()) {
            photo.getParentFile().mkdirs();
        }
        Files.copy(Paths.get(profile.photo), Paths.get(photo.getAbsolutePath()));

        ps.setString(11, "/media/avatar/" + largeUid);

        String smallUid = largeUid.replace(".jpg", "-sm.jpg");
        Thumbnails.of(photo).size(110, 110).toFile(new File(MEDIA_DIR + "/avatar/" + smallUid));

        ps.setString(12, "/media/avatar/" + smallUid);
        if (random.nextBoolean()) {
            ps.setString(13, getInfo());
        } else {
            ps.setNull(13, Types.VARCHAR);
        }
        ps.setString(14, PASSWORD_HASH);

        ps.setTimestamp(15, new Timestamp(System.currentTimeMillis()));

        if (random.nextBoolean()) {
            ps.setString(16, lowerCaseProfileName);
        } else {
            ps.setNull(16, Types.VARCHAR);
        }

        if (random.nextBoolean()) {
            ps.setString(17, "https://vk.com/" + lowerCaseProfileName);
        } else {
            ps.setNull(17, Types.VARCHAR);
        }
        if (random.nextBoolean()) {
            ps.setString(18, "https://facebook.com/" + lowerCaseProfileName);
        } else {
            ps.setNull(18, Types.VARCHAR);
        }
        if (random.nextBoolean()) {
            ps.setString(19, "https://linkedin.com/" + lowerCaseProfileName);
        } else {
            ps.setNull(19, Types.VARCHAR);
        }
        if (random.nextBoolean()) {
            ps.setString(20, "https://github.com/" + lowerCaseProfileName);
        } else {
            ps.setNull(20, Types.VARCHAR);
        }
        if (random.nextBoolean()) {
            ps.setString(21, "https://stackoverflow.com/" + lowerCaseProfileName);
        } else {
            ps.setNull(21, Types.VARCHAR);
        }
        ps.executeUpdate();
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                profile.id = generatedKeys.getLong(1);
            } else {
                throw new SQLException("Не присвоен ID в БД");
            }
        }

        ps.close();
    }

    private static String getInfo() {
        int endIndex = random.nextInt(SENTENCES.size());
        int startIndex = random.nextInt(endIndex);
        if (endIndex - startIndex > 4) {
            endIndex = startIndex + 3;
        }
        return StringUtils.join(SENTENCES.subList(startIndex, endIndex), " ");
    }
}
