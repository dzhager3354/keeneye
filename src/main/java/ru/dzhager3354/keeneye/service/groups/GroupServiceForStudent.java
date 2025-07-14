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
public class GroupServiceForStudent implements IGroupService {

    @Override
    public List<StudentGroup> getStudentGroups(AppUserDetails details) {
        return List.of(details.getUser().getStudent().getStudentGroup());
    }

    @Override
    public StudentGroup createStudentGroup(CreateStudentGroupDto dto, AppUserDetails details) {
        throw new AccessException();
    }

    @Override
    public StudentGroup updateStudentGroup(UpdateStudentGroupDto dto, AppUserDetails details) {
        throw new AccessException();
    }
}
