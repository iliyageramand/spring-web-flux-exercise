package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.domain.Person;
import com.example.springwebfluxexercise.domain.PersonCourse;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.mapper.CourseMapper;
import com.example.springwebfluxexercise.mapper.PersonMapper;
import com.example.springwebfluxexercise.repository.CourseRepository;
import com.example.springwebfluxexercise.repository.PersonCourseRepository;
import com.example.springwebfluxexercise.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonCourseServiceImpl implements PersonCourseService {
    private final PersonCourseRepository personCourseRepository;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final PersonMapper personMapper;

    public Flux<CourseDto> findCoursesByPersonId(Long id) {
        Flux<PersonCourse> personCourseFlux = personCourseRepository.findPersonCourseByPersonId(id);
        return extractPersonAndCourse(personCourseFlux)
                .map(personCourse -> {
                    CourseDto courseDto = courseMapper.toCourseDto(personCourse.getCourse());
                    courseDto.setInstructor(personMapper.toPersonDto(personCourse.getPerson()));
                    return courseDto;
                });
    }

    @Override
    public Flux<PersonDto> findStudentsByCourseId(Long id) {
        Flux<PersonCourse> personCourseFlux = personCourseRepository.findPersonCourseByCourseId(id);
        return extractPersonAndCourse(personCourseFlux)
                .map(personCourse -> personMapper.toPersonDto(personCourse.getPerson()));
    }

    private Flux<PersonCourse> extractPersonAndCourse(Flux<PersonCourse> personCourseFlux) {
        return personCourseFlux
                .flatMap(personCourse -> {
                    Long courseId = personCourse.getCourseId();
                    Long personId = personCourse.getPersonId();
                    Mono<Person> personMono = personRepository.findById(personId);
                    Mono<Course> courseMono = courseRepository.findById(courseId);
                    return Mono.zip(personMono, courseMono)
                            .flatMap(objects -> {
                                Person person = objects.getT1();
                                Course course = objects.getT2();
                                personCourse.setPerson(person);
                                personCourse.setCourse(course);
                                return Mono.just(personCourse);
                            });
                });
    }
}
