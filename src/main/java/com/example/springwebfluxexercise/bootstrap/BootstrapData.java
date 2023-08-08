package com.example.springwebfluxexercise.bootstrap;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.domain.Person;
import com.example.springwebfluxexercise.domain.PersonCourse;
import com.example.springwebfluxexercise.repository.CourseRepository;
import com.example.springwebfluxexercise.repository.PersonCourseRepository;
import com.example.springwebfluxexercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final PersonCourseRepository personCourseRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] titles = {"AP", "DS", "Math", "Physics", "AI", "ML", "Network", "security", "web", "SE"};
        for (int i = 0; i < 10; i++) {
            long min = LocalDate.of(1980, Month.JANUARY, 1).toEpochDay();
            long max = LocalDate.of(2005, Month.DECEMBER, 31).toEpochDay();
            LocalDate birthdate = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(min, max));

            Person person = Person.builder()
                    .nationalId(Integer.toString(i).repeat(10))
                    .firstName("firstName" + (i + 1))
                    .lastName("lastName" + (i + 1))
                    .birthdate(birthdate)
                    .build();
            Course course = Course.builder()
                    .instructorId((long) (i + 1))
                    .title(titles[i])
                    .build();
            personRepository.save(person).subscribe();
            courseRepository.save(course).subscribe();
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j)
                    continue;
                PersonCourse personCourse = PersonCourse.builder()
                        .personId((long) (j + 1))
                        .courseId((long) (i + 1))
                        .build();
                personCourseRepository.save(personCourse).subscribe();
            }
        }
    }
}
