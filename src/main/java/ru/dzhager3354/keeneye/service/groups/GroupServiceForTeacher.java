package ru.dzhager3354.keeneye.service.groups;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentGroupDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentGroupDto;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.exceptions.StudentGroupNotFoundException;
import ru.dzhager3354.keeneye.repository.StudentGroupRepository;
import ru.dzhager3354.keeneye.repository.TeacherRepository;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class GroupServiceForTeacher implements IGroupService {
    private final StudentGroupRepository studentGroupRepository;

    @Override
    public List<StudentGroup> getStudentGroups(AppUserDetails details) {
        return details.getUser().getTeacher().getStudentGroups();
    }

    @Override
    public StudentGroup createStudentGroup(CreateStudentGroupDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public StudentGroup updateStudentGroup(UpdateStudentGroupDto dto, AppUserDetails details) {
        StudentGroup group = studentGroupRepository.findById(dto.getId()).orElseThrow(StudentGroupNotFoundException::new);
        if (!Objects.equals(group.getTeacher().getId(), details.getUser().getTeacher().getId())) {
            throw new AccessException();
        }

        group.setName(dto.getName());
        return studentGroupRepository.save(group);
    }
}
