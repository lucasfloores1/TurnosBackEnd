package com.turnos.turnos.service.impl;

import com.turnos.turnos.DTO.PlanDTO;
import com.turnos.turnos.model.ObraSocial;
import com.turnos.turnos.model.Plan;
import com.turnos.turnos.repository.PlanRepository;
import com.turnos.turnos.service.IPlanService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public Set<PlanDTO> getPlanesByObraSocial(Long idObraSocial) {
        ObraSocial obraSocial = obraSocialService.getObraSocialById(idObraSocial);
        List<Plan> planes = planRepository.findByObraSocial(obraSocial);
        Set<PlanDTO> response = new HashSet<>();
        for ( Plan plan : planes ){
            PlanDTO planDTO = new PlanDTO();
            planDTO.setId(plan.getId());
            planDTO.setNombre(plan.getNombre());
            response.add(planDTO);
        }
        return response;
    }

    @Override
    public Plan createPlan(Plan plan) {
        
        return planRepository.save(plan);
        
    }

    @Override
    public void deletePlan(Long id) {
        
        planRepository.deleteById(id);
        
    }
    
}
