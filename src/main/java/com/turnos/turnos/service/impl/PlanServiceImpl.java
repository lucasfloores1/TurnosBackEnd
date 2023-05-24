package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.Plan;
import com.turnos.turnos.repository.PlanRepository;
import com.turnos.turnos.service.IPlanService;
import org.springframework.beans.factory.annotation.Autowired;


public class PlanServiceImpl implements IPlanService{
    
    @Autowired
    private PlanRepository planRepository;

    @Override
    public Plan getPlanById(Long id) {
        
        return planRepository.findById(id).orElse(null);
        
    }
    
}
