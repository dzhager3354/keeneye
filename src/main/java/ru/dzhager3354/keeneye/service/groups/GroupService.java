package ru.dzhager3354.keeneye.service.groups;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhager3354.keeneye.dto.CreateStudentGroupDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentGroupDto;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.exceptions.AccessException;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupService implements IGroupService {
    private final GroupServiceForAdmin adminService;
    private final GroupServiceForTeacher teacherService;
    private final GroupServiceForStudent studentService;

    @Override
    public List<StudentGroup> getStudentGroups(AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_STUDENT -> studentService.getStudentGroups(details);
            case ROLE_ADMIN -> adminService.getStudentGroups(details);
            case ROLE_TEACHER -> teacherService.getStudentGroups(details);
        };
    }

    @Override
    public StudentGroup createStudentGroup(CreateStudentGroupDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_ADMIN -> adminService.createStudentGroup(dto, details);
            case ROLE_TEACHER -> teacherService.createStudentGroup(dto, details);
            default -> throw new AccessException();
        };
    }

    @Override
    public StudentGroup updateStudentGroup(UpdateStudentGroupDto dto, AppUserDetails details) {
        return switch (details.getRole()) {
            case ROLE_ADMIN -> adminService.updateStudentGroup(dto, details);
            case ROLE_TEACHER -> teacherService.updateStudentGroup(dto, details);
            default -> throw new AccessException();
        };
    }
}
