package com.turnos.turnos.service;

import com.turnos.turnos.model.Plan;
import java.util.List;


public interface IPlanService {
    
    public Plan getPlanById( Long id );
    
    public List<Plan> getPlanes();
    
    public List<Plan> getPlanesByObraSocial(Long idObraSocial);
    
    public void createPlan (Plan plan);
    
    public void deletePlan (Long id);
    
}
