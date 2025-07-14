package ru.dzhager3354.keeneye.service.groups;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentGroupDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentGroupDto;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.entity.Teacher;
import ru.dzhager3354.keeneye.exceptions.StudentGroupNotFoundException;
import ru.dzhager3354.keeneye.exceptions.TeacherNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceForAdmin implements IGroupService {
    private final StudentGroupRepository studentGroupRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public List<StudentGroup> getStudentGroups(AppUserDetails details) {
        return studentGroupRepository.findAll();
    }

    @Override
    public StudentGroup createStudentGroup(CreateStudentGroupDto dto, AppUserDetails details) {
        Teacher teacher = dto.getTeacherId() != null ?
                teacherRepository.findById(dto.getTeacherId()).orElseThrow(TeacherNotFoundException::new)
                : null;

        return studentGroupRepository.save(
                StudentGroup.builder()
                        .name(dto.getName())
                        .teacher(teacher)
                        .build()
        );
    }

    @Override
    public StudentGroup updateStudentGroup(UpdateStudentGroupDto dto, AppUserDetails details) {
        if (dto.getId() == null || !studentGroupRepository.existsById(dto.getId())) {
            throw new StudentGroupNotFoundException();
        }

        Teacher teacher = dto.getTeacherId() != null ?
                teacherRepository.findById(dto.getTeacherId()).orElseThrow(TeacherNotFoundException::new)
                : null;

        return studentGroupRepository.save(
                StudentGroup.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .teacher(teacher)
                        .build()
        );
    }
}
