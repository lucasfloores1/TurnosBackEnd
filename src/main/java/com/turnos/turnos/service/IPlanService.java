package com.turnos.turnos.service;

import com.turnos.turnos.DTO.PlanDTO;
import com.turnos.turnos.model.Plan;
import java.util.List;
import java.util.Set;


public interface IPlanService {
    
    public Plan getPlanById( Long id );
    
    public List<Plan> getPlanes();
    
    public Set<PlanDTO> getPlanesByObraSocial(Long idObraSocial);
    
    public Plan createPlan(Plan plan);
    
    public void deletePlan (Long id);
    
}
