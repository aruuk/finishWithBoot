package com.example.finishwithboot.api;

import com.example.finishwithboot.model.Group;
import com.example.finishwithboot.service.GroupService;
import com.example.finishwithboot.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

//    @GetMapping("/groups")
//    private String getAllGroups(Model model){
//        model.addAttribute("groups", groupService.getAllGroups());
//        return "/group/getAllGroups";
//    }
//
//    @GetMapping("/groups/addGroup")
//    public String addGroup(Model model) {
//        model.addAttribute("group", new Group());
//        return "/group/addGroup";
//    }
//
//    @PostMapping("/{id}/saveGroup")
//    public String saveGroup(@ModelAttribute("group") Group group,
//                            @PathVariable Long id) {
//        groupService.saveGroup(group, id);
//        return "redirect:/groups/ " + id;
//    }
//
//    @GetMapping("/updateGroup/{groupId}")
//    private String editGroup(@PathVariable("groupId") Long id, Model model){
//        Group group = groupService.getById(id);
//        model.addAttribute("group", group);
//        return "group/saveUpdateGroup";
//    }
//
//    @PostMapping("/{id}/saveUpdateGroup")
//    public String saveUpdateGroup(@RequestParam("id") Long id,
//                                  @ModelAttribute("group") Group group) {
//        groupService.updateGroup(id, group);
//        return "redirect:/groups/ " + id;
//    }
//
//    @GetMapping("/{id}/deleteGroup")
//    public String deleteGroup(@PathVariable("id") Long id) {
//        groupService.deleteGroup(id);
//        return "redirect:/groups/ " + id;
//    }


    //by course id

    @GetMapping("/groups/{id}")
    public String getAllGroupsByCourseId(Model model, @PathVariable("id") Long id) {
        model.addAttribute("groupCourses", groupService.getAllGroup(id));
        model.addAttribute("courseId", id);
        return "/group/getAllGroups";
    }

    @GetMapping("/groups/{courseId}/addGroupByCourseId")
    public String addGroupByCourseId(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("newGroup", new Group());
        model.addAttribute("courseId", courseId);
        System.out.println("add");
        return "group/addGroupByCourseId";
    }

    @PostMapping("/{courseId}/saveGroupByCourseId")
    public String saveGroupByCourseId(@ModelAttribute("newGroup") Group group,
                                      @PathVariable("courseId") Long courseId) {
        System.out.println("save1");
        groupService.saveGroup(group, courseId);
        System.out.println("save2");
        return "redirect:/groups/" + courseId;
    }

    @GetMapping("/updateGroupByCourseId/{courseId}/{id}")
    public String updateGroupByCourseId(@PathVariable("id") Long id,
                                        @PathVariable("courseId") Long courseId, Model model) {
        Group group = groupService.getById(id);
        model.addAttribute("group", group);
        model.addAttribute("courseId", courseId);
        return "/group/updateGroupByCourseId";
    }

    @PostMapping("/{courseId}/{id}/saveUpdateGroupByCourseId")
    public String saveUpdateGroupByCourseId(@PathVariable("id") Long id,
                                            @PathVariable("courseId") Long courseId,
                                            @ModelAttribute("group") Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/groups/ " + courseId;

    }

    @GetMapping("/{courseId}/{id}/deleteGroupByCourseId")
    public String deleteGroupCourseId(@PathVariable Long courseId,
                                      @PathVariable("id") Long id) {
        groupService.deleteGroup(id);
        return "redirect:/groups/ " + courseId;
    }
}
