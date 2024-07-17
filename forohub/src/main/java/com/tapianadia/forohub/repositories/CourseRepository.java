package com.tapianadia.forohub.repositories;

import com.tapianadia.forohub.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
