package com.plantacion.employeemanagementapp.controller;

import com.plantacion.employeemanagementapp.model.domain.LeaveRequest;
import com.plantacion.employeemanagementapp.model.domain.Role;
import com.plantacion.employeemanagementapp.model.domain.Staff;
import com.plantacion.employeemanagementapp.model.dto.LeaveRequestDTO;
import com.plantacion.employeemanagementapp.model.dto.StaffDTO;
import com.plantacion.employeemanagementapp.service.LeaveRequestService;
import com.plantacion.employeemanagementapp.service.RoleService;
import com.plantacion.employeemanagementapp.service.StaffService;
import com.plantacion.employeemanagementapp.service.TimeRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final StaffService staffService;
    private final RoleService roleService;
    private final LeaveRequestService leaveRequestService;
    private final TimeRecordService timeRecordService;

    public AdminController(StaffService staffService, RoleService roleService, LeaveRequestService leaveRequestService, TimeRecordService timeRecordService) {
        this.staffService = staffService;
        this.roleService = roleService;
        this.leaveRequestService = leaveRequestService;
        this.timeRecordService = timeRecordService;
    }

    @ModelAttribute("staff_roles")
    public List<Role> staffRoles() {
        return roleService.getAllRoles();
    }

    @ModelAttribute("staffDTO")
    public StaffDTO staffDTOObject(){
        return new StaffDTO();
    }

    @GetMapping
    public String viewAdminDashboard(@ModelAttribute("staffDTO") StaffDTO staffDTO, Model model, Principal principal, HttpServletRequest request) {
        Staff staff = staffService.findStaffByEmail(principal.getName());
        model.addAttribute("admin", staff.getFirstname());
        model.addAttribute("staff_count", staffService.registeredStaffCount());
        model.addAttribute("leave_request_count", leaveRequestService.getCountOfLeaveRequests());
        model.addAttribute("staffs", staffService.adminGetRecentTenStaff());
        HttpSession session = request.getSession();
        session.setAttribute("AppSession", principal.getName());
        return "admin-dashboard";
    }

    @PostMapping("/create-staff")
    public String processAdminCreateStaff(@Valid @ModelAttribute("staffDTO") StaffDTO staffDTO, BindingResult result) {
        staffService.adminCreateStaff(staffDTO);
        return "admin-dashboard";
    }

    @GetMapping("/staffs")
    public String showAllStaffListPage(Model model, @RequestParam(defaultValue = "1") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Page paginatedStaffs = staffService.getPaginatedStaffs(PageRequest.of(page - 1, size));
        int totalPages = paginatedStaffs.getTotalPages();
        long totalItems = paginatedStaffs.getTotalElements();
        List<Staff> content = paginatedStaffs.getContent();

        model.addAttribute("staffs", content);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("currentPage", page);

        return "staff";
    }

    @GetMapping("/leave-request")
    public String showLeaveRequest(@ModelAttribute("leaveRequest")LeaveRequestDTO leaveRequestDTO, Model model){
        List<LeaveRequest> leaveRequestList = leaveRequestService.findAll();
        model.addAttribute("leaveRequests", leaveRequestList);
        return "leave-request";
    }

    @PostMapping("/leave-request")
    public String processLeaveRequest(@ModelAttribute("leaveRequest")LeaveRequestDTO leaveRequestDTO, Principal principal){
        leaveRequestService.createLeave(leaveRequestDTO, principal);
        return "redirect:/admin";
    }

    @GetMapping("/staff/{id}")
    public String showViewStaffPage(@PathVariable("id") Long id, Model model ){
        double hoursWorked = timeRecordService.calcHoursWorked(id);
        model.addAttribute("hoursWorked", hoursWorked);
        model.addAttribute("NoOfLeaveRequest", leaveRequestService.findByStaffId(id).size());
        return "view-staff";
    }
}
