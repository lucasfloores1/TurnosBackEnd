package com.turnos.turnos.service.impl;

import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.repository.PlanRepository;
import com.turnos.turnos.service.IPlanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements IPlanService{
    
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ObraSocialServiceImpl obraSocialService;

    @Override
    public Plan getPlanById(Long id) {
        
        return planRepository.findById(id).orElse(null);
        
    }

    @Override
    public List<Plan> getPlanes() {
        return planRepository.findAll();
    }

    @Override
    public List<Plan> getPlanesByObraSocial(Long idObraSocial) {
        ObraSocial obraSocial = obraSocialService.getObraSocialById(idObraSocial);
        return planRepository.findByObraSocial(obraSocial);
    }

    @Override
    public void createPlan(Plan plan) {
        
        planRepository.save(plan);
        
    }

    @Override
    public void deletePlan(Long id) {
        
        planRepository.deleteById(id);
        
    }
    
}
