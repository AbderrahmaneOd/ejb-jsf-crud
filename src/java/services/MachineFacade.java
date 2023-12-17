/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Employe;
import entities.Machine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hp
 */
@Stateless
public class MachineFacade extends AbstractFacade<Machine> {

    @PersistenceContext(unitName = "EmployeMachinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MachineFacade() {
        super(Machine.class);
    }


    public Map<Integer, Long> getMachinesAcquiredByYear() {
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT YEAR(m.dateAcquisition), COUNT(m) FROM Machine m GROUP BY YEAR(m.dateAcquisition)", Object[].class);

        List<Object[]> resultList = query.getResultList();

        // Convertir le résultat en une Map d'années et de nombre de machines
        Map<Integer, Long> machinesByYear = new HashMap<>();
        for (Object[] result : resultList) {
            Integer year = (Integer) result[0];
            Long count = (Long) result[1];
            machinesByYear.put(year, count);
        }

        return machinesByYear;
    }

    public List<Machine> findByEmployee(Employe employee) {
        TypedQuery<Machine> query = em.createQuery("SELECT m FROM Machine m WHERE m.employe = :employee", Machine.class);
        query.setParameter("employee", employee);
        return query.getResultList();
    }
}
