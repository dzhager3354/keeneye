package ru.dzhager3354.keeneye.service.groups;

import ru.dzhager3354.keeneye.dto.CreateStudentGroupDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentGroupDto;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.security.AppUserDetails;

import java.util.List;

public interface IGroupService {
    List<StudentGroup> getStudentGroups(AppUserDetails details);

    StudentGroup createStudentGroup(CreateStudentGroupDto dto, AppUserDetails details);

    StudentGroup updateStudentGroup(UpdateStudentGroupDto dto, AppUserDetails details);
}
