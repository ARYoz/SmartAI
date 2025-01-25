package com.example.SmartAI.Service;

import com.example.SmartAI.Model.Constraint_Employee;
import com.example.SmartAI.Model.Employee;
import com.example.SmartAI.Model.ShiftType;
import com.example.SmartAI.Repository.ConstraintRepo;
import com.example.SmartAI.Utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ConstraintService {

    @Autowired
    ConstraintRepo constraintRepo;

    public ConstraintService(ConstraintRepo constraintRepo)
    {
        this.constraintRepo = constraintRepo;
    }

    public Constraint_Employee addConstraint(Constraint_Employee constraint_employee)
    {
        boolean exist = constraintRepo.existsByEmployeeIDAndDateAndShiftType(constraint_employee.getEmployeeID(),
                constraint_employee.getDate(),constraint_employee.getShiftType());
        if(exist)
        {
            throw new RuntimeException("This constraint already exists for the employee on this date and shift.");
        }
        LocalDate startOfWeek = DateUtils.getStartOfWeek(constraint_employee.getDate());
        LocalDate endOfWeek = DateUtils.getEndOfWeek(constraint_employee.getDate());
        long weeklyConstraintCount = constraintRepo.countByEmployeeIDAndDateBetween(constraint_employee.getEmployeeID()
        ,startOfWeek,endOfWeek);
        if(weeklyConstraintCount > 2)
        {
            throw new RuntimeException("An employee cannot have more than 2 constraints in a single week.");
        }

        if(constraint_employee.getPriority()<1 || constraint_employee.getPriority()> 10)
        {
            throw new RuntimeException("Priority must be between 1 and 10.");
        }

        return constraintRepo.save(constraint_employee);
    }

    public Constraint_Employee getByEmloyeeId(Long id) // GET by ID
    {
        return constraintRepo.findById(id).orElseThrow(()-> new RuntimeException("This constraint does not exist"));
    }
    public List<Constraint_Employee> getAllConstraints() // GET ALL
    {
        return constraintRepo.findAll();
    }
    public Constraint_Employee UpdatedConstraint(Long id, Constraint_Employee UpdateConstraint) // UPDATE
    {
        Constraint_Employee existingConstraint = constraintRepo.getById(id);

        if(existingConstraint == null)
        {
            throw new RuntimeException("This Constraint not fount with this Id  " + id + "Employee number" );
        }

        existingConstraint.setReason(UpdateConstraint.getReason());
        existingConstraint.setDate(UpdateConstraint.getDate());
        existingConstraint.setShiftType(UpdateConstraint.getShiftType());
        existingConstraint.setPriority(UpdateConstraint.getPriority());

        return constraintRepo.save(existingConstraint);

    }


    public void DeleteConstraint(Long id)
    {
        constraintRepo.deleteById(id);
    }
    public List<Constraint_Employee> getConstraintByDate(LocalDate date)
    {
        return constraintRepo.findAllByDate(date);
    }
    public List<Constraint_Employee> getConstraintByShiftType(ShiftType shiftType)
    {
        return constraintRepo.findAllByShiftType(shiftType);
    }

    public  List<Constraint_Employee> getALLByShiftAndDate(LocalDate date, ShiftType shiftType)
    {
        return constraintRepo.findAllByDateAndShiftType(date,shiftType);
    }
    public boolean ConstraintExist(Long employeeID, LocalDate date, ShiftType shiftType)
    {
        return constraintRepo.existsByEmployeeIDAndDateAndShiftType(employeeID,date,shiftType);
    }
    public long CountConstraints(Long employeeID, LocalDate startDate, LocalDate endDate)
    {
        return constraintRepo.countByEmployeeIDAndDateBetween(employeeID,startDate,endDate);
    }

}
