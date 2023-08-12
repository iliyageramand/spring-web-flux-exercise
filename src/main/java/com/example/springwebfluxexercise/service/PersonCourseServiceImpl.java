package com.example.springwebfluxexercise.service;

import com.example.springwebfluxexercise.domain.Course;
import com.example.springwebfluxexercise.domain.Person;
import com.example.springwebfluxexercise.domain.PersonCourse;
import com.example.springwebfluxexercise.dto.IdDto;
import com.example.springwebfluxexercise.dto.PersonCourseDto;
import com.example.springwebfluxexercise.dto.course.CourseDto;
import com.example.springwebfluxexercise.dto.person.PersonDto;
import com.example.springwebfluxexercise.exception.AlreadyExistsException;
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
    private final PersonService personService;
    private final CourseService courseService;
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

    @Override
    public Mono<PersonCourseDto> takeCourse(Long studentId, IdDto courseIdDto) {
        Long courseId = courseIdDto.getId();
        Mono<Boolean> existsMono = personCourseRepository.existsByPersonIdAndCourseId(studentId, courseId);
        Mono<CourseDto> courseMono = courseService.findById(courseId);
        Mono<PersonDto> personMono = personService.findById(studentId);
        return Mono.zip(courseMono, personMono, existsMono)
                .flatMap(objects -> {
                    CourseDto courseDto = objects.getT1();
                    PersonDto personDto = objects.getT2();
                    Boolean recordExists = objects.getT3();
                    if (courseDto.getInstructor().getNationalId().equals(personDto.getNationalId())) {
                        return Mono.error(new AlreadyExistsException(
                                String.format("Person #%s is the instructor of course #%s", studentId, courseId)
                        ));
                    }
                    if (recordExists) {
                        return Mono.error(new AlreadyExistsException(
                                String.format("Person #%s has already taken course #%s", studentId, courseId)
                        ));
                    }
                    PersonCourse personCourse = PersonCourse.builder()
                            .personId(studentId)
                            .courseId(courseId)
                            .build();
                    return personCourseRepository.save(personCourse)
                            .map(pc -> new PersonCourseDto(courseDto, personDto));
                });
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
