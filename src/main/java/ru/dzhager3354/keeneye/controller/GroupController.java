package ru.dzhager3354.keeneye.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.dzhager3354.keeneye.dto.CreateStudentGroupDto;
import ru.dzhager3354.keeneye.dto.UpdateStudentGroupDto;
import ru.dzhager3354.keeneye.entity.StudentGroup;
import ru.dzhager3354.keeneye.security.AppUserDetails;
import ru.dzhager3354.keeneye.service.groups.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public List<StudentGroup> getAllGroups(@AuthenticationPrincipal AppUserDetails userDetails) {
        return groupService.getStudentGroups(userDetails);
    }

    @PostMapping
    public StudentGroup createStudentGroup(@RequestBody CreateStudentGroupDto dto, @AuthenticationPrincipal AppUserDetails userDetails) {
        return groupService.createStudentGroup(dto, userDetails);
    }

    @PutMapping
    public StudentGroup updateStudentGroup(@RequestBody UpdateStudentGroupDto dto, @AuthenticationPrincipal AppUserDetails userDetails) {
        return groupService.updateStudentGroup(dto, userDetails);
    }
}
